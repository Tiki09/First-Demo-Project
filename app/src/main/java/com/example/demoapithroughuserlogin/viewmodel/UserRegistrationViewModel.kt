package com.example.demoapithroughuserlogin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapithroughuserlogin.database.SignUpDatabase
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class UserRegistrationViewModel : ViewModel() {


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

    fun isAddressValidate(address: String): Boolean {
        if (address.isEmpty()) {
            return false
        } else {
            return true
        }
    }

    val EmailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}"

    fun isEmailValidate(
        email: String,
        callBack: (Boolean, String) -> Unit
    ) {
        if (email.isEmpty()) {
            callBack(false, "Email can't be empty")
        } else {
            val pattern = Pattern.compile(EmailPattern)
            val matcher = pattern.matcher(email)
            callBack(
                matcher.matches(),
                "It must follow the patterns, Email address must contain a single '@' symbol"
            )
        }
    }

    fun userRegistrationData(
        id: Long,
        address: String,
        email: String,
        database: SignUpDatabase
    ) {
        viewModelScope.launch {
            database.signUpDao().updateAddressAndEmail(id, address, email)

        }
    }
}