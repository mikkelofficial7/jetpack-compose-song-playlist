package com.example.musicplaylistapp.music.model

data class MusicModel(
    var status: Int,
    var musics: List<ItemSong>
)

data class ItemSong(
    var title: String,
    var singer: String,
    var image: String,
    var file: String
)