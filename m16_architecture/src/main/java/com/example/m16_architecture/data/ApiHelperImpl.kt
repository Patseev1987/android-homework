package com.example.m16_architecture.data


import com.example.m16_architecture.domain.Activity
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val mapper: ActivityMapper,
    private val apiService: ApiService,
    private val flow: MutableSharedFlow<Unit>
) : ApiHelper {


    override fun getActivity(): Flow<Activity> {
        return flow {
            emit(mapper.getActivityFromBoredApiToActivity(apiService.loadActivity()))
            flow.collect {
                emit(mapper.getActivityFromBoredApiToActivity(apiService.loadActivity()))
            }
        }
    }

    override suspend fun updateActivity() {
        flow.emit(Unit)
    }
}