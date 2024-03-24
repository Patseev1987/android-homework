package ru.bogdan.m15_room.domain

import ru.bogdan.m15_room.data.database.WordEntity

class SaveWordUseCase(private val applicationRepository: ApplicationRepository) {
    suspend fun save(word:String) = applicationRepository.saveWord(word)
}