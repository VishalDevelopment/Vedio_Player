package com.example.vedioplayer.Data_Layer.model.Repo

import android.app.Application
import android.provider.MediaStore
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.vedioplayer.Data_Layer.model.VedioFile
import com.example.vedioplayer.Domain_Layer.VedioAppRepo
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class vedioAppRepoImpl @Inject constructor():VedioAppRepo  {

   override fun getAllVedio(applicationContext: Application): Flow<ArrayList<VedioFile>> {
        val allVideo = arrayListOf<VedioFile>()
        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.TITLE,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.DATE_ADDED,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.DISPLAY_NAME
        )
        val uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        val cursor = applicationContext.contentResolver.query(uri, projection, null, null, null)

        if (cursor != null && cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                val id = cursor.getString(0)
                val path = cursor.getString(1)
                val title = cursor.getString(2)
                val size = cursor.getString(3)
                val dateAdded = cursor.getString(4)
                val duration = cursor.getString(5)
                val filename = cursor.getString(6)

                val vedioFile = VedioFile(id, path, title, filename, size, duration, dateAdded)
                allVideo.add(vedioFile)
                if (cursor.isLast) {
                    break
                }
            }
            cursor.close()
        }
        return flow{
         emit(allVideo)
        }
    }
}