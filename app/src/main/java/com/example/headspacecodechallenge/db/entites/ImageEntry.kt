package com.example.headspacecodechallenge.db.entites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.headspacecodechallenge.model.ImageItem

@Entity(tableName = "imageTable")
class ImageEntry(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "url")
    var url: String,

    @ColumnInfo(name = "author")
    var author: String,

    @ColumnInfo(name = "width")
    var width: Int,

    @ColumnInfo(name = "height")
    var height: Int,

    @ColumnInfo(name = "download_url")
    var download_url: String
) {
    constructor() : this("", "", 0, 0, "")

    fun getImageEntryFromResponse(response: ImageItem): ImageEntry {
        this.url = response.url
        this.author = response.author
        this.width = response.width
        this.height = response.height
        this.download_url = response.download_url
        return this
    }
}