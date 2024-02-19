package com.example.demoapithroughuserlogin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapithroughuserlogin.database.SignUpDatabase
import com.example.demoapithroughuserlogin.entity.SignUpDetailsEntity
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    val passwordValidation =
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$\n"

    fun isNameValidate(name: String): Boolean {
        if (name.length >= 3) {
            return true
        } else {
            return false
        }
    }


    fun isMobValidate(mob: String): Boolean {
        if (mob.isEmpty()) {
            return false
        } else if (mob.length != 10) {
            return false
        } else {
            return true
        }
    }

    fun isPasswordValidate(password: String): Boolean {
        return if (password.isEmpty()) {
            false
        } else if ((password.length >= 8)
            && (password.length <= 15)
        ) {
            true
        } else password.equals(passwordValidation)
    }

    fun isBothPasswordAreSame(p1: String, p2: String): Boolean {
        return p1 == p2
    }

    fun insertUserIfNotExists(
        mobileNumber: String,
        name: String,
        password: String,
        database: SignUpDatabase,
        callBack : (Boolean, String, String, Long) -> Unit
    ){
        viewModelScope.launch {
            val existingUserCount = database.signUpDao().checkMobileNumberExists(mobileNumber)
            if (existingUserCount == 0) {
                val user = SignUpDetailsEntity(
                    System.currentTimeMillis(),
                    mobile = mobileNumber,
                    name = name,
                    pwd = password
                )
                database.signUpDao().insertUser(user)
                callBack(true, "User Inserted", user.name, user.id)
                // message = "User inserted successfully"
            } else {
                callBack(false,"User with mobile number $mobileNumber already exists","", 0L)
            }
        }
    }

}