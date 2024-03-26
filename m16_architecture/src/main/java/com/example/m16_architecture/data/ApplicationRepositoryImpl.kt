package com.example.m16_architecture.data

import com.example.m16_architecture.domain.Activity
import com.example.m16_architecture.domain.ApplicationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApplicationRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper
) : ApplicationRepository {
    override fun getActivity(): Flow<Activity> {
        return apiHelper.getActivity()
    }

    override suspend fun updateActivity() {
        apiHelper.updateActivity()
    }
}