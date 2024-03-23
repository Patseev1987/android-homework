package ru.bogdan.m14_retrofit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.bogdan.m14_retrofit.domain.ApplicationRepository
import ru.bogdan.m14_retrofit.domain.GetUserUseCase
import ru.bogdan.m14_retrofit.domain.UpdateUserUseCase
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val updateUserUseCase: UpdateUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val loadingFlow:MutableSharedFlow<State>,
    scope:CoroutineScope
):ViewModel() {


    val state: Flow<State> = getUserUseCase.getUser()
        .map{State.Result(it) as State }
        .onStart { emit(State.Loading) }
        .mergeWith(loadingFlow)
        .buffer().stateIn(
           scope =  scope,
            started = SharingStarted.Lazily,
            initialValue = State.Loading
        )



    private    fun <T> Flow<T>.mergeWith(anotherFlow:Flow<T>):Flow<T>{
         return   merge(this,anotherFlow)
        }

    fun updateData(){
        viewModelScope.launch {
            loadingFlow.emit(State.Loading)
            updateUserUseCase.update()
        }
    }
}