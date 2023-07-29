package com.example.musicplaylistapp

import android.content.Context
import android.media.MediaPlayer
import android.os.CountDownTimer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.musicplaylistapp.music.Music
import com.example.musicplaylistapp.music.MusicImpl
import com.example.musicplaylistapp.music.model.ItemSong
import com.example.musicplaylistapp.music.model.MusicModel
import com.google.gson.Gson
import java.io.*


class ViewModels(private val context: Context) : ViewModel() {
    private val music: Music by lazy { MusicImpl() }

    var isPlay by mutableStateOf(false)
    var isStop by mutableStateOf(true)
    var musicProgress by mutableStateOf(0f)
    var playListPosition by mutableStateOf(-1)
    var musicMediaPlayer: MediaPlayer? by mutableStateOf(null)

    private lateinit var countDownMusic: CountDownTimer

    inline fun <reified T> getMusicPlaylist(context: Context) : T {
        val inputStream: InputStream = context.resources.openRawResource(R.raw.list_of_music)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        inputStream.use { inputStream ->
            val reader: Reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            var n: Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)
            }
        }

        return Gson().fromJson(writer.toString(), T::class.java)
    }

    fun isMediaPlayerInitialized() : Boolean {
        return musicMediaPlayer != null
    }

    fun getCurrentSongInfo(): ItemSong? {
        if(playListPosition == -1) return null
        return getMusicPlaylist<MusicModel>(context).musics[playListPosition]
    }

    fun setupMusicPlayer(musicFileName: String = "", position: Int) {
        if(musicFileName.isEmpty()) return
        if(musicMediaPlayer != null) stopMusic()

        musicMediaPlayer =  music.init(context, music.getPlaylistByName(musicFileName))
        playMusic()
        playListPosition = position
    }

    fun playMusic() {
        if(!isMediaPlayerInitialized()) return

        isStop = false
        isPlay = true

        music.play(musicMediaPlayer!!)

        countDownMusic = object : CountDownTimer(musicMediaPlayer!!.duration.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                musicProgress = musicMediaPlayer!!.currentPosition.toFloat()/musicMediaPlayer!!.duration.toFloat()
            }
            override fun onFinish() {
               val listMusic = getMusicPlaylist<MusicModel>(context).musics // play random music after one song finishes
               val numbRandom = (listMusic.indices).random()
               val randomMusicFileName = listMusic[numbRandom].file

                setupMusicPlayer(randomMusicFileName, numbRandom)
            }
        }
        countDownMusic.start()
    }

    fun pauseMusic() {
        if(!isMediaPlayerInitialized()) return
        if(!::countDownMusic.isInitialized) return

        isPlay = false
        musicProgress = musicMediaPlayer!!.currentPosition.toFloat()/musicMediaPlayer!!.duration.toFloat()

        countDownMusic.cancel()
        music.pause(musicMediaPlayer!!)
    }

    fun stopMusic() {
        if(!isMediaPlayerInitialized()) return
        if(!::countDownMusic.isInitialized) return

        isStop = true
        isPlay = false
        musicProgress = 0f

        countDownMusic.cancel()
        music.stop(musicMediaPlayer!!)
    }

    fun playNextSong() {
        if(!isMediaPlayerInitialized()) return

        val listMusic = getMusicPlaylist<MusicModel>(context).musics
        val position = if(playListPosition  < (listMusic.size - 1)) playListPosition + 1 else 0

        setupMusicPlayer(listMusic[position].file, position)
    }

    fun playPrevSong() {
        if(!isMediaPlayerInitialized()) return

        val listMusic = getMusicPlaylist<MusicModel>(context).musics
        val position = if(playListPosition  > 0) playListPosition - 1 else listMusic.size - 1

        setupMusicPlayer(listMusic[position].file, position)
    }
}