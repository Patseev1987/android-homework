package ru.bogdan.m15_room.domain

class GetWordsUseCase (private  val applicationRepository: ApplicationRepository) {
   suspend fun getWords() = applicationRepository.loadWords()
}