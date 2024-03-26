package com.example.m16_architecture.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m16_architecture.domain.GetActivityUseCase
import com.example.m16_architecture.domain.UpdateActivityUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getActivityUseCase: GetActivityUseCase,
    private val loadingFlow: MutableSharedFlow<State>,
    private val updateActivityUseCase: UpdateActivityUseCase
) : ViewModel() {
    val state: Flow<State> = getActivityUseCase()
        .map { State.Result(it) as State }
        .onStart { emit(State.Loading) }
        .mergeWith(loadingFlow)
        .buffer()
        .stateIn(
            scope = viewModelScope, started = SharingStarted.Lazily, initialValue = State.Loading
        )


    private fun <T> Flow<T>.mergeWith(anotherFlow: Flow<T>): Flow<T> {
        return merge(this, anotherFlow)
    }

    fun updateData() {
        viewModelScope.launch {
            loadingFlow.emit(State.Loading)
            updateActivityUseCase()
        }
    }

}