package com.example.musicplaylistapp.music

import android.content.Context
import android.media.MediaPlayer

interface Music {
    fun init(context: Context, fileName: Int) : MediaPlayer

    fun play(mediaPlayer: MediaPlayer)

    fun pause(mediaPlayer: MediaPlayer)

    fun stop(mediaPlayer: MediaPlayer)

    fun nextTo(mediaPlayer: MediaPlayer)

    fun prevTo(mediaPlayer: MediaPlayer)

    fun getPlaylistByName(fileName: String): Int
}