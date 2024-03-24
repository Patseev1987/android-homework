package ru.bogdan.m15_room.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert
    suspend fun insert(word: WordEntity)

    @Query("update words set  counter = counter + 1 where word = :word")
    suspend fun update(word: String)

    @Query("select * from words")
    fun getWords(): Flow<List<WordEntity>>

    @Query("select * from words where word = :id")
    suspend fun getWord(id: String): WordEntity?
    @Query("delete from words")
    suspend fun clearTable()
}