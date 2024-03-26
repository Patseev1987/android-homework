package com.example.m16_architecture.presentation

import com.example.m16_architecture.domain.Activity

sealed class State {

    object Loading : State()

    class Result(val activity: Activity) : State()


}