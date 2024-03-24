package ru.bogdan.m15_room.domain

import kotlinx.coroutines.flow.Flow
import ru.bogdan.m15_room.data.database.WordEntity

interface ApplicationRepository {
    suspend fun saveWord( word:String )

   fun loadWords(): Flow<List<Word>>

  suspend  fun loadWord( id:String):WordEntity?

  suspend fun clearTable()
}