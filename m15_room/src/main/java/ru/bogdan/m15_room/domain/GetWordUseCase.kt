package ru.bogdan.m15_room.domain

import ru.bogdan.m15_room.data.database.WordEntity

class GetWordUseCase(private val applicationRepository: ApplicationRepository) {
    suspend fun getWord(id:String):WordEntity? = applicationRepository.loadWord(id)
}