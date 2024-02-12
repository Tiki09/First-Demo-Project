package com.example.demoapithroughuserlogin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demoapithroughuserlogin.repository.APIRepository

class ViewModelFactory(private val apiRepo: APIRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlbumViewModel(apiRepo) as T
    }
}