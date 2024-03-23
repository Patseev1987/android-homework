package ru.bogdan.m14_retrofit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.bogdan.m14_retrofit.data.ApiHelper


class MainViewModel(private val apiHelper: ApiHelper):ViewModel() {

    private val loadingFlow:MutableSharedFlow<State> = MutableSharedFlow()

    val state: Flow<State> = apiHelper.getUser()
        .map{State.Result(it) as State }
        .onStart { emit(State.Loading) }
        .mergeWith(loadingFlow)



        fun <T> Flow<T>.mergeWith(anotherFlow:Flow<T>):Flow<T>{
         return   merge(this,anotherFlow)
        }

    fun updateData(){
        viewModelScope.launch {
            loadingFlow.emit(State.Loading)
            apiHelper.updateData()
        }
    }
}