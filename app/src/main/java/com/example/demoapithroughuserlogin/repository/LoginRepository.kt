package com.example.demoapithroughuserlogin.repository

import com.example.demoapithroughuserlogin.database.SignUpDatabase
import com.example.demoapithroughuserlogin.entity.SignUpDetailsEntity

class LoginRepository(
    private val database: SignUpDatabase
) {

    suspend fun loginUser(mobile:String, password:String):SignUpDetailsEntity?{
        return database.signUpDao().login(mobile, password)
    }
}