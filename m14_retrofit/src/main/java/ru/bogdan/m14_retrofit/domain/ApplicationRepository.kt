package ru.bogdan.m14_retrofit.domain

import kotlinx.coroutines.flow.Flow

interface ApplicationRepository {

    fun getUser(): Flow<SimpleUser>
    suspend fun updateUser()
}