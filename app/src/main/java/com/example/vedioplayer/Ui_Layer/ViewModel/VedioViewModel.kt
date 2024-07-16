package com.example.vedioplayer.Ui_Layer.ViewModel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vedioplayer.Data_Layer.model.Repo.vedioAppRepoImpl
import com.example.vedioplayer.Data_Layer.model.VedioFile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VedioViewModel @Inject constructor(val repoImpl: vedioAppRepoImpl,val application: Application):ViewModel() {
    var showUI =  MutableStateFlow(false)
    var VedioList = MutableStateFlow<List<VedioFile>>(emptyList())
    var isLoading = mutableStateOf(true)
    fun getAllVedios(){
        viewModelScope.launch {
            repoImpl.getAllVedio(application).collectLatest {
                VedioList.value = it
            }
        }
        isLoading.value = false
    }
}