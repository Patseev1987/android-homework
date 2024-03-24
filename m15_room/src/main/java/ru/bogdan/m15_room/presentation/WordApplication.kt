package ru.bogdan.m15_room.presentation

import android.app.Application
import ru.bogdan.m15_room.di.DaggerApplicationComponent

class WordApplication : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}