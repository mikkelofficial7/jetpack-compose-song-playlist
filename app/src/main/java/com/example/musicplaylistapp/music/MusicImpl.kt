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

    override fun getPlaylistByName(context: Context, fileName: String): Int {
        return context.resources.getIdentifier(fileName, "raw", context.packageName)
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
}