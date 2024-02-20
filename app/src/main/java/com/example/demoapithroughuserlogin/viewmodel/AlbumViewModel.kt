package com.example.demoapithroughuserlogin.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapithroughuserlogin.model.Album
import com.example.demoapithroughuserlogin.repository.APIRepository
import kotlinx.coroutines.launch

class AlbumViewModel(
    private val repository: APIRepository
) : ViewModel() {
    val albumMutableLiveData: MutableLiveData<Album> = MutableLiveData()

    fun fetchAlbumData() {
        viewModelScope.launch {
            try {
                val album_response = repository.getAlbumData()
                albumMutableLiveData.value = album_response
            } catch (e: Exception) {
                Log.e("api", "getAlbumsData: ${e.message}")
            }
        }
    }
}