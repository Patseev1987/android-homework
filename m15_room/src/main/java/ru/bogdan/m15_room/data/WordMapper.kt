package ru.bogdan.m15_room.data

import ru.bogdan.m15_room.data.database.WordEntity
import ru.bogdan.m15_room.domain.Word
import javax.inject.Inject

class WordMapper @Inject constructor() {

    fun getWordFromWordEntity(wordEntity: WordEntity): Word {
        return Word(word = wordEntity.word, counter = wordEntity.counter)
    }
}