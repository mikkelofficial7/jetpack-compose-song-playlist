package com.example.musicplaylistapp.uicomponent

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.musicplaylistapp.ViewModels
import com.example.musicplaylistapp.music.model.MusicModel
import com.example.musicplaylistapp.ui.theme.White1000

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ViewListofMusic(viewModel: ViewModels) {
    val playlist = viewModel.getMusicPlaylist<MusicModel>(LocalContext.current)

    Box(
        Modifier
            .background(color = White1000)
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        LazyVerticalGrid(cells = GridCells.Adaptive(100.dp)) {
            items(playlist.musics.size) { position ->
                Box(modifier = Modifier.pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            viewModel.setupMusicPlayer(playlist.musics[position].file, position)
                        })
                }
                ) {
                    Column {
                        Image(
                            painter = rememberAsyncImagePainter(playlist.musics[position].image),
                            contentDescription = null,
                            modifier = Modifier
                                .width(300.dp)
                                .heightIn(200.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = playlist.musics[position].title,
                            modifier = Modifier
                                .heightIn(50.dp)
                                .padding(horizontal = 5.dp)
                        )
                    }
                }
            }
        }
    }
}