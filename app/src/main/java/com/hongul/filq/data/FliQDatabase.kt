package com.hongul.filq.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [BusinessCardEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    SNSConverter::class,
    AvatarConverter::class
)
abstract class FliQDatabase : RoomDatabase() {
    abstract fun businessCardDao(): BusinessCardDao

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
                    .addTypeConverter(SNSConverter())
                    .addTypeConverter(AvatarConverter())
                    .fallbackToDestructiveMigration()
                    .build().also { INSTANCE = it }
            }
        }
    }
}