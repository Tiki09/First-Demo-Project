package com.example.demoapithroughuserlogin.model

class Album : ArrayList<AlbumItem>()

data class AlbumItem(
    val id: Int,
    val title: String,
    val userId: Int
)