package com.example.headspacecodechallenge.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.headspacecodechallenge.db.dao.ImageDao
import com.example.headspacecodechallenge.db.entites.ImageEntry

@Database(entities = [ImageEntry::class], version = 1, exportSchema = false)
abstract class ImageDatabase : RoomDatabase() {

    abstract fun imageDao(): ImageDao

    companion object {
        private var INSTANCE: ImageDatabase? = null
        fun getInstance(context: Context): ImageDatabase? {
            synchronized(ImageDatabase::class) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    ImageDatabase::class.java,
                    "image-database"
                )
                    .build()
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}