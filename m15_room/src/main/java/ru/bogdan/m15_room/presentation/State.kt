package ru.bogdan.m15_room.presentation

import ru.bogdan.m15_room.domain.Word

sealed class State {
    class Result(val words:List<Word>):State()
    class Error(val msg:String):State()
}