package com.example.demoapithroughuserlogin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel:ViewModel() {
//
//    fun returnString():String{
//        return "Hello .........."
//    }

    val passwordValidation = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$\n"
    fun isNameValidate(name:String): Boolean {
        if(name.length>=3){
            return true
        }
        else{
            return false
        }
    }

    fun isMobValidate(mob:String): Boolean {
        if (mob.isEmpty()){
            return false
        }
        else if (mob.length<10){
            return false
        }
        else {
            return true
        }
    }

    fun isPasswordValidate(password:String):Boolean{
        if (password.isEmpty()){
            return false
        }
        else if ((password.length >= 8)
                    && (password.length <= 15)) {
            return true
        }
        else if (!password.equals(passwordValidation)){
            return false
        }
        else{
            return true
        }
    }

    fun isBothPasswordAreSame(p1:String,p2:String): Boolean {
        return p1==p2
    }
}