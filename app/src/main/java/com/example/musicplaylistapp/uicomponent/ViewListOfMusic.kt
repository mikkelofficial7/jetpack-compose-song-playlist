package com.example.musicplaylistapp.uicomponent

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.musicplaylistapp.R
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
    ) {
        Column() {
            LazyVerticalGrid(
                cells = GridCells.Adaptive(100.dp),
                Modifier.weight(1f)
            ) {
                items(playlist.musics.size) { position ->
                    Box(
                        modifier = Modifier
                            .heightIn(50.dp)
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onTap = {
                                        viewModel.setupMusicPlayer(playlist.musics[position].file, position)
                                    })
                            }
                    ) {
                        Column(
                            Modifier.alpha(if(position == viewModel.playListPosition && !viewModel.isStop) 0.5f else 1f)
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(playlist.musics[position].image),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(300.dp)
                                    .heightIn(200.dp),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Row {
                                Image(
                                    painterResource(R.drawable.ic_play),
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop,
                                    colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onBackground),
                                    modifier = Modifier.widthIn(min = 10.dp)
                                )
                                Text(
                                    text = playlist.musics[position].title,
                                    fontSize = 12.sp,
                                    modifier = Modifier
                                        .heightIn(10.dp)
                                        .padding(horizontal = 5.dp)
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(85.dp))
        }
    }
}