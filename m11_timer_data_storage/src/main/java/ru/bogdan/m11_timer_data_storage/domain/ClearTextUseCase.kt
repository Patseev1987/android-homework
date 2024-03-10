package ru.bogdan.m11_timer_data_storage.domain

class ClearTextUseCase(private val repository: Repository) {
    fun clearText(){
        repository.clearText()
    }
}