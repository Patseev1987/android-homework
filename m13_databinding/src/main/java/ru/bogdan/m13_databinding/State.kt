package ru.bogdan.m13_databinding

sealed class State{
    object Waiting : State()
    object Loading : State()

    object Ready:State()
    class Result(val value: String) : State()
}

