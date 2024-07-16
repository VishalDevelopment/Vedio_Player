package com.example.vedioplayer.Ui_Layer.Screens

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.vedioplayer.Data_Layer.model.VedioFile
import com.example.vedioplayer.R
import com.example.vedioplayer.Ui_Layer.Navigation.NavigationRoute
import com.example.vedioplayer.Ui_Layer.ViewModel.VedioViewModel


data class VedioList(
    val title: String,
    val duration: String,
)

@Composable

fun VedioScreen(selectedIcon: MutableState<ImageVector>, navController: NavHostController) {

    BackHandler {
        if(selectedIcon.value == Icons.Default.PlayArrow){
            selectedIcon.value = Icons.Default.Home
            navController.navigate(NavigationRoute.HomeScreen){
                popUpTo(0)
            }
        }
    }

    val viewModel : VedioViewModel = hiltViewModel()
    viewModel.getAllVedios()
    val vedioList = viewModel.VedioList.collectAsState()
    Log.d("VEDIOLIST","${vedioList.value}")
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(top = 1.dp)) {
        LazyColumn {
            items(vedioList.value) {
               VedioScreenItem(it,navController)
            }
        }
    }
}

@Composable
fun VedioScreenItem(vedio: VedioFile, navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth(.95f)
            .height(80.dp)
            .padding(vertical = 3.dp, horizontal = 5.dp)
            .clickable {
                navController.navigate(NavigationRoute.PlayerScreen(vedio.path.toString()))
            }
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.video_icon),
                modifier = Modifier.size(60.dp),
                contentDescription = null
            )
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp), verticalArrangement = Arrangement.Center
            ) {
                Text(text = "${vedio.title}", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Text(text = "${vedio.duration}", fontSize = 12.sp, fontWeight = FontWeight.Normal)
            }
        }
    }
}