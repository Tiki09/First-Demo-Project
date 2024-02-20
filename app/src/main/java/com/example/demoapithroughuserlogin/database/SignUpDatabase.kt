package com.example.demoapithroughuserlogin.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.demoapithroughuserlogin.dao.SignUpDAO
import com.example.demoapithroughuserlogin.entity.SignUpDetailsEntity

@Database(entities = [SignUpDetailsEntity::class], version = 3)
abstract class SignUpDatabase:RoomDatabase() {

    abstract fun signUpDao():SignUpDAO

    companion object{
        @Volatile
        private var INSTANCE : SignUpDatabase ?= null

        fun getDatabse(context: Context):SignUpDatabase{
            if (INSTANCE==null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        SignUpDatabase::class.java,
                        "sign up_DB"
                    ) .fallbackToDestructiveMigration() //allows you to make schema changes without writing migration code
                        .allowMainThreadQueries()
                        .build()
                }
            }
            INSTANCE?.openHelper?.writableDatabase
            return INSTANCE!!
        }
    }
}