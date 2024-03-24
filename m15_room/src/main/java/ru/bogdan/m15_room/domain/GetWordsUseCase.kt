package ru.bogdan.m15_room.domain

import javax.inject.Inject

class GetWordsUseCase @Inject constructor(private val applicationRepository: ApplicationRepository) {
    fun getWords() = applicationRepository.loadWords()
}