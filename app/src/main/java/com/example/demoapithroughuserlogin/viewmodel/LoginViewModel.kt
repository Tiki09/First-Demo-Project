package com.example.demoapithroughuserlogin.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapithroughuserlogin.dao.SignUpDAO
import com.example.demoapithroughuserlogin.database.SignUpDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {
    fun loginData(
        mobile: String,
        password: String,
        database: SignUpDatabase,
        callBack: (Boolean, String, Long) -> Unit
    ) {

        viewModelScope.launch {
            val userData =
                database.signUpDao().login(mobile, password)


            if (userData != null) {
                callBack(true, userData.name, userData.id)
            } else {
                callBack(false, "", 0L)
            }
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


    fun isValidPassword(password: String, callBack: (Boolean, String) -> Unit) {
        if (password.isEmpty()) {
            callBack(false, "Empty Password")
        } else if (!((password.length >= 8) && (password.length <= 15))) {
            callBack(false, "Password length must be at least 8 characters to 15 characters")
        } else {
            val PASSWORD_PATTERN =
                "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$"
            val pattern = Pattern.compile(PASSWORD_PATTERN)
            val matcher = pattern.matcher(password)
            callBack(
                matcher.matches(),
                "must have at least 1 lowercase and at least 1 uppercase letter.\n" +
                        "It must have one special character\n" +
                        "It must have at least 1 digit"
            )
        }

    }


}
