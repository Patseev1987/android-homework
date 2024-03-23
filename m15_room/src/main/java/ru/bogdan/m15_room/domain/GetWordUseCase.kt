package ru.bogdan.m15_room.domain

class GetWordUseCase(private val applicationRepository: ApplicationRepository) {
    fun getWord(id:String):Word = applicationRepository.loadWord(id)
}