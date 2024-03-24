package ru.bogdan.m15_room.domain

import kotlinx.coroutines.flow.Flow
import ru.bogdan.m15_room.data.database.WordEntity
import ru.bogdan.m15_room.presentation.State

interface ApplicationRepository {
    suspend fun saveWord(word: String)

    fun loadWords(): Flow<State>

    suspend fun loadWord(id: String): WordEntity?

    suspend fun clearTable()
}