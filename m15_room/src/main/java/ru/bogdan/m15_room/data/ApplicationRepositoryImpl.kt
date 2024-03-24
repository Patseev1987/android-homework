package ru.bogdan.m15_room.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.bogdan.m15_room.data.database.Dao
import ru.bogdan.m15_room.data.database.WordEntity
import ru.bogdan.m15_room.domain.ApplicationRepository
import ru.bogdan.m15_room.domain.Word
import javax.inject.Inject

class ApplicationRepositoryImpl @Inject constructor(
    private val dao: Dao,
    private val scope: CoroutineScope,
    private val mapper: WordMapper
) : ApplicationRepository {
    override suspend fun saveWord(word: String) {
        val tempWord = dao.getWord(word)
        if (tempWord == null) {
            dao.insert(WordEntity(word))
        } else {
            dao.update(word)
        }
    }

    override fun loadWords(): Flow<List<Word>> {
        return dao.getWords().map {
            it.map { word -> mapper.getWordFromWordEntity(word) }
        }
    }

    override suspend fun loadWord(id: String): WordEntity? {
        return dao.getWord(id)
    }

    override suspend fun clearTable() {
        dao.clearTable()
    }
}