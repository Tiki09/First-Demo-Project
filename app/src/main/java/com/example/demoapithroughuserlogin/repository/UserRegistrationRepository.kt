package com.example.demoapithroughuserlogin.repository

import com.example.demoapithroughuserlogin.database.SignUpDatabase

class UserRegistrationRepository(
    private val database: SignUpDatabase
) {
    suspend fun updateAddressAndEmail(
        id: Long, address: String, email: String
    ){
        database.signUpDao().updateAddressAndEmail(id, address, email)
    }
}