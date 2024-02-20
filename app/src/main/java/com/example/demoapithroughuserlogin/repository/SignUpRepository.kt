package com.example.demoapithroughuserlogin.repository

import com.example.demoapithroughuserlogin.dao.SignUpDAO
import com.example.demoapithroughuserlogin.database.SignUpDatabase
import com.example.demoapithroughuserlogin.entity.SignUpDetailsEntity

class SignUpRepository(
    private val database: SignUpDatabase
) {
    suspend fun insertUser(signUpDetailsEntity: SignUpDetailsEntity){
        database.signUpDao().insertUser(signUpDetailsEntity)
   }

        fun isMobileNumberExists(mobile : String):Int{
        return database.signUpDao().checkMobileNumberExists(mobile)
    }
}