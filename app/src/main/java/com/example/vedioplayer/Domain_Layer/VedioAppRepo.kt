package com.example.vedioplayer.Domain_Layer

import android.app.Application
import com.example.vedioplayer.Data_Layer.model.VedioFile
import kotlinx.coroutines.flow.Flow

interface VedioAppRepo {
    fun getAllVedio(application: Application): Flow<List<VedioFile>>

}