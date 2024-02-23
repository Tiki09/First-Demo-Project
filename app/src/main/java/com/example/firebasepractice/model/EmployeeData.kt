package com.example.firebasepractice.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.SerializedName

@Parcelize
class EmployeeData(
   @SerializedName("name") var name: String ?,

    @SerializedName("role") var role: String ?,

    @SerializedName("programmingLanguage") var programmingLanguage: String?
):Parcelable{
    constructor():this(
        "","",""
    )
}

