package com.example.demoapithroughuserlogin.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapithroughuserlogin.database.SignUpDatabase
import com.example.demoapithroughuserlogin.entity.SignUpDetailsEntity
import com.example.demoapithroughuserlogin.repository.SignUpRepository
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class SignUpViewModel(
    private val signUpRepository: SignUpRepository
) : ViewModel() {

    val passwordRegex = Regex("^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")

    fun isNameValidate(name: String): Boolean {
        return if (name.length >= 3) {
            true
        } else {
            false
        }
    }

    fun isMobValidate(mob: String): Boolean {
        return if (mob.isEmpty()) {
            false
        } else if (mob.length != 10) {
            false
        } else {
            true
        }
    }

    fun isPasswordValidate(password: String): Boolean {
        return if (password.isEmpty()) {
            false
        } else if (!((password.length >= 8) && (password.length <= 15))) {
            false
        } else {
            password.matches(passwordRegex)
        }
    }

    fun isBothPasswordAreSame(p1: String, p2: String): Boolean {
        return p1 == p2
    }

    fun insertUserIfNotExists(
        mobileNumber: String,
        name: String,
        password: String,
        callBack: (Boolean, String, String, Long, String) -> Unit
    ) {
        viewModelScope.launch {
            val existingUserCount = signUpRepository.isMobileNumberExists(mobileNumber)
            if (existingUserCount == 0) {
                val user = SignUpDetailsEntity(
                    System.currentTimeMillis(),
                    mobile = mobileNumber,
                    name = name,
                    pwd = password
                )
                signUpRepository.insertUser(user)
                callBack(true, "User Inserted", user.name, user.id, user.mobile)
                // message = "User inserted successfully"
            } else {
                callBack(false, "User with mobile number $mobileNumber already exists", "", 0L, "")
            }
        }
    }

}