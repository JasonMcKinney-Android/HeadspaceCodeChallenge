package com.example.headspacecodechallenge.repository

import com.example.headspacecodechallenge.db.ImageDatabase
import com.example.headspacecodechallenge.db.entites.ImageEntry
import com.example.headspacecodechallenge.model.ImageItem
import com.example.headspacecodechallenge.network.WebService

class ImageRepositoryImpl(
    private val webService: WebService,
    private val database: ImageDatabase?
) : ImageRepository {

    override suspend fun webImages(page: Int): Array<ImageItem> {
        return webService.getImages(page)
    }

    override suspend fun allImages(): List<ImageEntry> {
        if (database != null) {
            return database.imageDao().getAllImages()
        }
        return emptyList()
    }

    override suspend fun insertImage(image: ImageEntry) {
        if (database != null) {
            database.imageDao().insertImages(image)
        }
    }
}