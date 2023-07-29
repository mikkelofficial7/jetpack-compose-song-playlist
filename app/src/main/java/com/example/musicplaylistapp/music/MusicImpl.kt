package com.example.musicplaylistapp.music

import android.content.Context
import android.media.MediaPlayer
import com.example.musicplaylistapp.R

class MusicImpl : Music {
    override fun init(context: Context, fileName: Int): MediaPlayer {
        val mediaPlayer = MediaPlayer.create(context, fileName)
        mediaPlayer.setVolume(100f, 100f)
        return mediaPlayer
    }

    override fun play(mediaPlayer: MediaPlayer) {
        mediaPlayer.start()
    }

    override fun pause(mediaPlayer: MediaPlayer) {
        mediaPlayer.pause()
    }

    override fun stop(mediaPlayer: MediaPlayer) {
        mediaPlayer.pause()
        mediaPlayer.seekTo(0)
    }

    override fun nextTo(mediaPlayer: MediaPlayer) {

    }

    override fun prevTo(mediaPlayer: MediaPlayer) {

    }

    override fun getPlaylistByName(fileName: String): Int {
        return when(fileName) {
            "song_01" -> R.raw.song_01
            "song_02" -> R.raw.song_02
            "song_03" -> R.raw.song_03
            "song_04" -> R.raw.song_04
            "song_05" -> R.raw.song_05
            "song_06" -> R.raw.song_06
            "song_07" -> R.raw.song_07
            "song_08" -> R.raw.song_08
            "song_09" -> R.raw.song_09
            "song_10" -> R.raw.song_10
            else -> 0
        }

    }
}