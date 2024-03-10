package ru.bogdan.m11_timer_data_storage.domain

class GetTextUseCase(private val repository: Repository) {
    fun getText(): String{
        return repository.getText()
    }
}