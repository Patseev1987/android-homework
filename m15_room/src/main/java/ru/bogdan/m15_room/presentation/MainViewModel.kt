package ru.bogdan.m15_room.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.bogdan.m15_room.domain.CleanTableUseCase
import ru.bogdan.m15_room.domain.GetWordsUseCase
import ru.bogdan.m15_room.domain.SaveWordUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getWordsUseCase: GetWordsUseCase,
    private val saveWordUseCase: SaveWordUseCase,
    private val cleanTableUseCase: CleanTableUseCase
) : ViewModel() {

    val state = getWordsUseCase.getWords()


    fun saveWord(word: String) {
        viewModelScope.launch {
            saveWordUseCase.save(word)
        }
    }

    fun clearDatabase() {
        viewModelScope.launch { cleanTableUseCase.cleanTable() }
    }


}