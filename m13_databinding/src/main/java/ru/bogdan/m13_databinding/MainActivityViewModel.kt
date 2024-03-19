package ru.bogdan.m13_databinding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivityViewModel : ViewModel() {
    private var cacheRequest: String? = null

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Result(cacheRequest))
    val state = _state.asStateFlow()


    val searchString: MutableStateFlow<String> = MutableStateFlow("")


    private var job: Job? = null

    private var results = listOf(
        """We couldn't find anything about "%s"""", """"%s" - Not found!""", "Connection error!"
    )

    private val scope = viewModelScope

    init {
        viewModelScope.launch {
            searchString
                .debounce(500)
                .filter {
                    it.length > 2
                }
                .collect {
                    loading()
                }
        }
    }

    private fun loading() {
        job = scope.launch {
            _state.value = State.Loading
            delay(5000)
            val result = String.format(
                results[Random.nextInt(results.size)], searchString.value
            )
            cacheRequest = result
            _state.value = State.Result(cacheRequest)
        }
    }


    fun cancel() {
        job?.cancel()
        _state.value = State.Result("")
    }


}