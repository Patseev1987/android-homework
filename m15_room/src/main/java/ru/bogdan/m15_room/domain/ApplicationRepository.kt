package ru.bogdan.m15_room.domain

import kotlinx.coroutines.flow.Flow

interface ApplicationRepository {
    fun saveWord( word:Word )

   suspend fun loadWords(): Flow<List<Word>>

    fun loadWord( id:String):Word
}