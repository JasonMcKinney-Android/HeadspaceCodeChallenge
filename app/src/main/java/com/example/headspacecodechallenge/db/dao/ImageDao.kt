package com.example.headspacecodechallenge.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.headspacecodechallenge.db.entites.ImageEntry

@Dao
interface ImageDao {

    @Query("SELECT * FROM imageTable")
    suspend fun getAllImages(): List<ImageEntry>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertImages(image: ImageEntry): Long

}