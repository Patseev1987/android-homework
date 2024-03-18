package ru.bogdan.m13_databinding

sealed class State{
    object Loading : State()
    class Result(val value: String? = null) : State()
}

