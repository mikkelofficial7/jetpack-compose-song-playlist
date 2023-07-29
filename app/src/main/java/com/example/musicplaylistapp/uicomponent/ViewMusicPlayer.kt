package com.example.musicplaylistapp.uicomponent

import android.R.attr.maxLines
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.musicplaylistapp.R
import com.example.musicplaylistapp.ViewModels
import com.example.musicplaylistapp.extension.convertMillisToMinute
import com.example.musicplaylistapp.ui.theme.Black800


@Composable
fun ViewMusicPlayer(viewModel: ViewModels) {
    val musicData = viewModel.musicMediaPlayer
    val songInfo = viewModel.getCurrentSongInfo()

    Column(
        Modifier
            .fillMaxHeight()
            .fillMaxHeight()
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = songInfo?.title.orEmpty(),
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp)
            )
            Text(
                text = "${musicData?.currentPosition.convertMillisToMinute()} / ${musicData?.duration.convertMillisToMinute()}",
                modifier = Modifier
                    .widthIn(10.dp)
                    .padding(horizontal = 10.dp)
            )
        }
        LinearProgressIndicator(progress = viewModel.musicProgress, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp))
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .background(Black800)
        ) {
            Row() {
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painterResource(R.drawable.ic_previous),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxHeight()
                        .alpha(if (viewModel.isMediaPlayerInitialized()) 1f else 0.3f)
                        .widthIn(min = 50.dp)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onPress = {
                                    viewModel.playPrevSong()
                                }
                            )
                        }
                )
                Spacer(modifier = Modifier.width(20.dp))
                Image(
                    if(viewModel.isPlay) painterResource(R.drawable.ic_pause) else painterResource(R.drawable.ic_play),
                    contentDescription = "icon play/pause",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxHeight()
                        .widthIn(min = 50.dp)
                        .alpha(if (viewModel.isMediaPlayerInitialized()) 1f else 0.3f)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onPress = {
                                    if (!viewModel.isPlay) {
                                        viewModel.playMusic()
                                    } else {
                                        viewModel.pauseMusic()
                                    }
                                },
                            )
                        }
                )
                if(!viewModel.isStop) {
                    Image(
                        painterResource(R.drawable.ic_stop),
                        contentDescription = "icon stop",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxHeight()
                            .alpha(if (viewModel.isMediaPlayerInitialized()) 1f else 0.3f)
                            .widthIn(min = 50.dp)
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onPress = {
                                        viewModel.stopMusic()
                                    },
                                )
                            }
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                Image(
                    painterResource(R.drawable.ic_next),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxHeight()
                        .alpha(if (viewModel.isMediaPlayerInitialized()) 1f else 0.3f)
                        .widthIn(min = 50.dp)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onPress = {
                                    viewModel.playNextSong()
                                }
                            )
                        }
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}