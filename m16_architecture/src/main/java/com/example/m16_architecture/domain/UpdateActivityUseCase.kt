package com.example.m16_architecture.domain

import javax.inject.Inject

class UpdateActivityUseCase @Inject constructor(private val applicationRepository: ApplicationRepository) {
  //  suspend fun updateActivity() = applicationRepository.updateActivity()

    suspend operator fun invoke () {
        applicationRepository.updateActivity()
    }
}