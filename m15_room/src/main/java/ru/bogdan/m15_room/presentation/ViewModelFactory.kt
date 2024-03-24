package ru.bogdan.m15_room.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.bogdan.m15_room.data.database.Dao
import ru.bogdan.m15_room.domain.ApplicationRepository

class ViewModelFactory(private val applicationRepository: ApplicationRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(applicationRepository) as T
        }else{
            throw RuntimeException("Unknown ViewModel")
        }
    }
}