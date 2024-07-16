package com.example.vedioplayer.Ui_Layer.Screens

import android.media.browse.MediaBrowser
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import com.example.vedioplayer.Ui_Layer.Navigation.NavigationRoute
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player.REPEAT_MODE_OFF
import androidx.media3.common.MediaItem.Builder
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView

@Composable
fun PlayerScreen(VedioPath: NavigationRoute.PlayerScreen) {
    val path = VedioPath.vedioUri
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

        val context = LocalContext.current
        val exoPlayer = remember {
            ExoPlayer.Builder(context)
                .build()
                .apply{
                    val mediaItem = MediaItem.fromUri(path.toUri())
                    setMediaItem(mediaItem)
                    prepare()
                    playWhenReady=true
                }
        }

        DisposableEffect(AndroidView(factory = {PlayerView(context).apply {
            player = exoPlayer
        }
        }, update = {
            it.player = exoPlayer
        }
        )) {
            onDispose { exoPlayer.release() }
        }
    }
}