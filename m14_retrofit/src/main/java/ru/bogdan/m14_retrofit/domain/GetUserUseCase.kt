package ru.bogdan.m14_retrofit.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor (private val applicationRepository: ApplicationRepository) {
     fun getUser(): Flow<SimpleUser> = applicationRepository.getUser()
}