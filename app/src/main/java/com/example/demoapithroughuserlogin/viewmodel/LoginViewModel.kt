package com.example.demoapithroughuserlogin.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapithroughuserlogin.dao.SignUpDAO
import com.example.demoapithroughuserlogin.database.SignUpDatabase
import com.example.demoapithroughuserlogin.entity.SignUpDetailsEntity
import com.example.demoapithroughuserlogin.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class LoginViewModel(
    private val loginRepository: LoginRepository
) : ViewModel() {
    fun loginData(
        mobile: String,
        password: String,
        callBack: (Boolean, String, Long, String?) -> Unit
    ) {
        viewModelScope.launch {
            val userData =
                loginRepository.loginUser(mobile, password)

            if (userData != null) {
                callBack(true, userData.name, userData.id, userData.mobile)
            } else {
                callBack(false, "", 0L, "")
            }
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

    fun isValidPassword(password: String, callBack: (Boolean, String) -> Unit) {
        if (password.isEmpty()) {
            callBack(false, "Empty Password")
        } else if (!((password.length >= 8) && (password.length <= 15))) {
            callBack(false, "Password length must be at least 8 characters to 15 characters")
        } else {

            val passwordRegex = Regex("^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")

            if (password.matches(passwordRegex)) {
                callBack(true, "Valid Password")
            } else {
                callBack(
                    false, "must have at least 1 lowercase and at least 1 uppercase letter.\n" +
                            "It must have one special character\n" +
                            "It must have at least 1 digit"
                )
            }
        }

    }
}
