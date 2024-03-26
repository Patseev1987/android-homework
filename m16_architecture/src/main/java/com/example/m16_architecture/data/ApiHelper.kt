package com.example.m16_architecture.data

import com.example.m16_architecture.domain.Activity
import kotlinx.coroutines.flow.Flow


interface ApiHelper {
    fun getActivity(): Flow<Activity>
    suspend fun updateActivity()
}