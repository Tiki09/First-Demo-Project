package com.example.demoapithroughuserlogin.viewmodel

import androidx.lifecycle.ViewModel
import java.util.regex.Matcher
import java.util.regex.Pattern


class LoginViewModel : ViewModel() {

    val mob = "9938573004"
    val password = "Tikeswari@123"

    val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$"

    fun isMobValidate(mob: String): Boolean {
        if (mob.isEmpty()) {
            return false
        } else if (mob.length < 10) {
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
        }else {
            val pattern = Pattern.compile(PASSWORD_PATTERN)
            val matcher = pattern.matcher(password)
            callBack(matcher.matches(),"must have at least 1 lowercase and at least 1 uppercase letter.\n" +
                    "It must have one special character\n" +
                    "It must have at least 1 digit")
        }

    }
}