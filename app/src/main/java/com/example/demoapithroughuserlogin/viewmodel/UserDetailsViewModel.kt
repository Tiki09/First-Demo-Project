package com.example.demoapithroughuserlogin.viewmodel

import androidx.lifecycle.ViewModel
import com.example.demoapithroughuserlogin.model.UserDetails
import java.util.regex.Pattern

class UserDetailsViewModel:ViewModel() {

    val userList = mutableListOf<UserDetails>()

}