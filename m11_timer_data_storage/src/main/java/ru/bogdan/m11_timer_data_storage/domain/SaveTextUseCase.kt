package ru.bogdan.m11_timer_data_storage.domain

class SaveTextUseCase(private val repository: Repository) {
    fun saveText(text: String){
        repository.saveText(text)
    }
}