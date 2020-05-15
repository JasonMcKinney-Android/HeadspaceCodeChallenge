package com.example.headspacecodechallenge.repository

import com.example.headspacecodechallenge.model.ImageItem

interface ImageRepository {

    suspend fun webImages(page: Int): Array<ImageItem>
}