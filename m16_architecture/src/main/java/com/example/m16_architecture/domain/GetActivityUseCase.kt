package com.example.m16_architecture.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetActivityUseCase @Inject constructor(private val applicationRepository: ApplicationRepository) {
   // fun getActivity() = applicationRepository.getActivity()

    operator fun invoke(): Flow<Activity> {
      return  applicationRepository.getActivity()
    }
}