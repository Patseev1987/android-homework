package com.example.m12_mvvm

sealed class State

object Waiting : State()
object Loading : State()

object Ready:State()
class Result(val value: String) : State()