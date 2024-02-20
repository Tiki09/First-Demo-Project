package com.example.demoapithroughuserlogin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapithroughuserlogin.database.SignUpDatabase
import com.example.demoapithroughuserlogin.repository.UserRegistrationRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class UserRegistrationViewModel(
    private val userRegistrationRepository: UserRegistrationRepository
) : ViewModel() {

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
    ) {
        viewModelScope.launch {
            userRegistrationRepository.updateAddressAndEmail(id, address, email)

        }
    }
}