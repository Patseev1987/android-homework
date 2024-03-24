package ru.bogdan.m15_room.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.bogdan.m15_room.domain.ApplicationRepository

class MainViewModel(private val applicationRepository: ApplicationRepository) : ViewModel() {

    val state = applicationRepository.loadWords()


    fun saveWord(word: String) {
        viewModelScope.launch {
            applicationRepository.saveWord(word)
        }
    }

    fun clearDatabase() {
        viewModelScope.launch { applicationRepository.clearTable() }

    }


}