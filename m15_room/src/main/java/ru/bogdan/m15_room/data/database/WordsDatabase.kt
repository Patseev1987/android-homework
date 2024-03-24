package ru.bogdan.m15_room.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WordEntity::class], exportSchema = false, version = 1)
abstract class WordsDatabase() : RoomDatabase() {

    abstract val wordDao: Dao

    companion object {
        @Volatile
        private var INSTANCE: WordsDatabase? = null

        fun getInstance(application: Application): WordsDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        application,
                        WordsDatabase::class.java,
                        "words"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}
