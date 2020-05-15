package com.example.headspacecodechallenge.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.headspacecodechallenge.repository.ImageRepository
import com.example.headspacecodechallenge.viewmodel.MainViewModel

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val imageRepository: ImageRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(imageRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
