package ru.bogdan.m14_retrofit.data

import kotlinx.coroutines.flow.Flow
import ru.bogdan.m14_retrofit.domain.SimpleUser

interface ApiHelper {
    fun getUser(): Flow<SimpleUser>
    suspend fun updateData()
}