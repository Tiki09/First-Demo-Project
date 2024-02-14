package com.example.demoapithroughuserlogin.viewmodel

import androidx.lifecycle.ViewModel
import com.example.demoapithroughuserlogin.model.UserDetails

class UserDetailsViewModel:ViewModel() {

    val userList = mutableListOf<UserDetails>()

}