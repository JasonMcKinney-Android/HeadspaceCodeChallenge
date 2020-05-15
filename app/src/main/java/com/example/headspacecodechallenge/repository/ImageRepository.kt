package com.example.headspacecodechallenge.repository

import com.example.headspacecodechallenge.db.entites.ImageEntry
import com.example.headspacecodechallenge.model.ImageItem

interface ImageRepository {
    //Retrofit
    suspend fun webImages(page: Int): Array<ImageItem>

    //Room
    //Get All
    suspend fun allImages(): List<ImageEntry>

    //Insert One
    suspend fun insertImage(image: ImageEntry)

}