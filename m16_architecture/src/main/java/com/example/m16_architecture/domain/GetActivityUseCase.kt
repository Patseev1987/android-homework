package com.example.m16_architecture.domain

import javax.inject.Inject

class GetActivityUseCase @Inject constructor(private val applicationRepository: ApplicationRepository) {
    fun getActivity() = applicationRepository.getActivity()
}