package ru.bogdan.m15_room.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.merge
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

    private val intermediateState: MutableSharedFlow<State> = MutableSharedFlow()
    val state = getWordsUseCase.getWords()
        .mergeWith(intermediateState)


    private fun <T> Flow<T>.mergeWith(anotherFlow: Flow<T>): Flow<T> {
        return merge(this, anotherFlow)
    }


    fun saveWord(word: String) {
        viewModelScope.launch {

            if (checkWord(word)) {
                saveWordUseCase.save(word)
            }else{
                setError()
            }
        }
    }

    fun clearDatabase() {
        viewModelScope.launch { cleanTableUseCase.cleanTable() }
    }

    private fun setError() {
        viewModelScope.launch {
            intermediateState.emit(State.Error("You must use only letters and one '-' in the middle of word !" +
                "\nWithout digits and other signs"))
        }
    }


    private fun checkWord(word: String): Boolean {
        val regex = """([a-zA-Zа-яА-Я]+)(-)?([a-zA-Zа-яА-Я]+)""".toRegex()
        return   word.matches(regex)
    }

}