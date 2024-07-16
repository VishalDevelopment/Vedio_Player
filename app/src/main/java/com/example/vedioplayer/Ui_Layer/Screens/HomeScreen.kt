package com.example.vedioplayer.Ui_Layer.Screens

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.vedioplayer.R
import com.example.vedioplayer.Ui_Layer.ViewModel.VedioViewModel

data class Folder(
    val name: String,
    val vedios: Int,
)

@Composable
//@Preview(showSystemUi = true)
fun HomeScreen() {


    val folderList = mutableListOf(
        Folder("Whatsapp Gif", 8),
        Folder("Instagram Download", 2),
        Folder("Download", 3),
        Folder("Fb Vedios", 11),
        Folder("Travel Photos", 7),
        Folder("Work Documents", 9),
        Folder("Music Playlist", 8),
        Folder("Ringtones", 5),
        // Add 11 more folders with sizes around 8
        Folder("Screenshots", 6),
        Folder("Recipes", 10),
        Folder("Games", 8),
        Folder("Movies", 12),
        Folder("Books", 4),
        Folder("Social Media", 7),
        Folder("Finances", 8),
        Folder("Templates", 9),
        Folder("Notes", 6),
        Folder("Archives", 11),
        Folder("Backups", 8)
    )
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(top = 1.dp)) {
        LazyColumn {
            items(folderList){
                HomeScreenItem(it)
            }
        }
    }
}

@Composable
//@Preview
fun HomeScreenItem(folder: Folder) {
    Box(
        modifier = Modifier
            .fillMaxWidth(.95f)
            .height(80.dp)
            .padding(vertical = 3.dp, horizontal = 5.dp)
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.folder),
                modifier = Modifier.size(60.dp),
                contentDescription = null
            )
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp), verticalArrangement = Arrangement.Center
            ) {
                Text(text = folder.name, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Text(text = "${folder.vedios} Vedios", fontSize = 12.sp, fontWeight = FontWeight.Normal)
            }
        }
    }
}