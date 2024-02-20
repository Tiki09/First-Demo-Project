package com.example.demoapithroughuserlogin.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.demoapithroughuserlogin.entity.SignUpDetailsEntity
import com.example.demoapithroughuserlogin.model.UserDetails

@Dao
interface SignUpDAO {
    @Insert
    suspend fun insertUser(user: SignUpDetailsEntity)

    @Query("SELECT * FROM SignUpDetailsEntity WHERE mobile = :mobile AND pwd = :password")
    suspend fun login(mobile: String, password: String): SignUpDetailsEntity?

    @Query("UPDATE SignUpDetailsEntity SET address = :address, email = :email where id =:id")
    suspend fun updateAddressAndEmail(id: Long, address: String, email: String)

    @Query("SELECT COUNT(*) FROM SignUpDetailsEntity WHERE mobile = :mobileNumber")
    fun checkMobileNumberExists(mobileNumber: String): Int

    @Query("select * from SignUpDetailsEntity")
    fun getData(): LiveData<List<UserDetails>>
}