package com.example.headspacecodechallenge.repository

import com.example.headspacecodechallenge.model.ImageItem
import com.example.headspacecodechallenge.network.WebService

class ImageRepositoryImpl(private val webService: WebService) : ImageRepository {

    override suspend fun webImages(page: Int): Array<ImageItem> {
        return WebService.instance.getImages(page)
    }
}