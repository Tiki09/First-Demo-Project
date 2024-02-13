package com.example.demoapithroughuserlogin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class UserDetails(
    val id: String,
    val name: String,
    val address: String,
    val contact: String,
    val email: String
):Parcelable{

}
