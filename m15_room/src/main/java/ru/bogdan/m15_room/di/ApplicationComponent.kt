package ru.bogdan.m15_room.di

import android.app.Application
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Component.Factory
import ru.bogdan.m15_room.data.ApplicationRepositoryImpl
import ru.bogdan.m15_room.domain.ApplicationRepository
import ru.bogdan.m15_room.presentation.MainActivity

@ApplicationScope
@Component(modules = [ProvidesModule::class, ViewModelsModule::class, DataModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)


    @Factory
    interface ComponentFactory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}