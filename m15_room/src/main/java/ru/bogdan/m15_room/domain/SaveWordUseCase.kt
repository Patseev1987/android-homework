package ru.bogdan.m15_room.domain

class SaveWordUseCase(private val applicationRepository: ApplicationRepository) {
    fun save(word:Word) = applicationRepository.saveWord(word)
}