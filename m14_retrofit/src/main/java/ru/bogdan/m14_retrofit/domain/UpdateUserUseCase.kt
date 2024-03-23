package ru.bogdan.m14_retrofit.domain

import javax.inject.Inject

class UpdateUserUseCase @Inject constructor (private val applicationRepository: ApplicationRepository) {
     suspend fun update() = applicationRepository.updateUser()
}