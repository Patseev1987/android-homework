package com.example.m16_architecture.domain

import kotlinx.coroutines.flow.Flow

interface ApplicationRepository {
    fun getActivity(): Flow<Activity>

    suspend fun updateActivity()
}