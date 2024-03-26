package com.example.m16_architecture.data

import com.example.m16_architecture.data.pojo.ActivityFromBoredApi
import com.example.m16_architecture.domain.Activity
import javax.inject.Inject

class ActivityMapper @Inject constructor() {

    fun getActivityFromBoredApiToActivity(
        activityFromBoredApi: ActivityFromBoredApi
    ): Activity {
        return Activity(
            activityFromBoredApi.activity,
            activityFromBoredApi.type
        )
    }
}