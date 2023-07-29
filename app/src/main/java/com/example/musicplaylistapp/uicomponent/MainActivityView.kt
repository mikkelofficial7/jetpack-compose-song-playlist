package com.example.musicplaylistapp.uicomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.musicplaylistapp.*
import com.example.musicplaylistapp.ui.theme.White1000

@Composable
fun MainActivityView(viewModel: ViewModels) {
    BoxWithConstraints(
        Modifier
            .background(color = White1000)
            .fillMaxHeight()
    ) {
        ViewListofMusic(viewModel)
        ViewMusicPlayer(viewModel)
    }
}