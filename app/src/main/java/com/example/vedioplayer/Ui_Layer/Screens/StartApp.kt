package com.example.vedioplayer.Ui_Layer.Screens

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.vedioplayer.Ui_Layer.Navigation.AppNavigation
import com.example.vedioplayer.Ui_Layer.ViewModel.VedioViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun StartApp() {
    val viewmodel: VedioViewModel = hiltViewModel()
    val managePermission =
        rememberPermissionState(permission = Manifest.permission.READ_MEDIA_VIDEO)
    val mediaPermissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                viewmodel.showUI.value = true
            } else {
                viewmodel.showUI.value = false
            }
        }
    LaunchedEffect(key1 = mediaPermissionLauncher) {
        if (!managePermission.status.isGranted) {
            mediaPermissionLauncher.launch(Manifest.permission.READ_MEDIA_VIDEO)
        } else {
            viewmodel.showUI.value = true
        }

    }

    val state = viewmodel.showUI.collectAsState()
    Box(contentAlignment = Alignment.Center){
        if (state.value == false) {
            Text(text = "Please Allow Permission to use this App")
        } else {
            AppNavigation()
        }
    }
}