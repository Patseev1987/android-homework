package com.example.m16_architecture.di

import com.example.m16_architecture.presentation.MainActivity
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

}