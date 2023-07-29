package com.example.musicplaylistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.musicplaylistapp.ui.theme.MusicPlaylistAppTheme
import com.example.musicplaylistapp.uicomponent.MainActivityView

class MainActivity : ComponentActivity() {
    private val viewModel by lazy {
        ViewModels(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MusicPlaylistAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainActivityView(viewModel)
                }
            }
        }
    }

    override fun onDestroy() {
        viewModel.stopMusic()
        super.onDestroy()
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MusicPlaylistAppTheme {
            MainActivityView(viewModel)
        }
    }
}