package ru.bogdan.m14_retrofit.presentation

import ru.bogdan.m14_retrofit.domain.SimpleUser


sealed class State {
    object Loading:State()

    class Result(val user: SimpleUser):State()

}