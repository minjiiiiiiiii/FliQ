package com.hongul.fliq.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hongul.fliq.data.card.CardDao
import com.hongul.fliq.data.card.CardEntity
import com.hongul.fliq.data.tag.TagDao
import com.hongul.fliq.data.tag.TagEntity
import com.hongul.fliq.data.user.UserDao
import com.hongul.fliq.data.user.UserEntity

@Database(
    entities = [
        CardEntity::class,
        TagEntity::class,
        UserEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class FliQDatabase: RoomDatabase() {
    abstract fun cardDao(): CardDao
    abstract fun tagDao(): TagDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: FliQDatabase? = null

        fun getDatabase(context: Context): FliQDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    FliQDatabase::class.java,
                    "fliq_database"
                )
                    .fallbackToDestructiveMigration()
                    .build().also { INSTANCE = it }
            }
        }
    }
}