package com.example.demoapithroughuserlogin.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity()
data class SignUpDetailsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val mobile:String,
    val name:String,
    val pwd:String,
    val address: String = "",
    val email: String = ""
    )