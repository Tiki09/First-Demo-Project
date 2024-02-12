package com.example.demoapithroughuserlogin.repository

import com.example.demoapithroughuserlogin.model.Album
import com.example.demoapithroughuserlogin.network.RetrofitBuilder

class APIRepository {
suspend fun getAlbumData():Album = RetrofitBuilder.api.getAlbum()
}