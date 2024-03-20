package ru.bogdan.m14_retrofit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.bogdan.m14_retrofit.data.ApiHelper


class MainViewModel(private val apiHelper: ApiHelper):ViewModel() {
    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Start)
    val state = _state.asStateFlow()

    init {
        getUser()
    }

private    fun getUser(){
        _state.value = State.Loading
        viewModelScope.launch {
            apiHelper.getUser().collect{
                _state.value = State.Result(it)
            }
        }
    }

    fun updateData(){
        apiHelper.updateData()
    }
}