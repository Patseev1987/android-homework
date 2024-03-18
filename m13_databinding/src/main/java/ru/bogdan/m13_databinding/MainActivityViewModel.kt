package ru.bogdan.m13_databinding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivityViewModel : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Ready)
    val state = _state.asStateFlow()

    private val _request: MutableStateFlow<String> = MutableStateFlow("")

    @OptIn(FlowPreview::class)
    val request = _request.asStateFlow()
        .debounce(1000)
        .filter {
            it.length > 2
        }

    private var job: Job? = null

    private var results = listOf(
        """We couldn't find anything about "%s"""", """"%s" - Not found!""", "Connection error!"
    )

    private val scope = viewModelScope

    init {
        viewModelScope.launch {
            request.collect {
            loading(it)
            }
        }
    }

    private fun loading( request:String) {
        job = scope.launch {
            _state.value = State.Loading
            delay(5000)
            val result = String.format(
                results[Random.nextInt(results.size)], request
            )
            _state.value = State.Result(result)
        }
    }


    fun setRequest(request: String) {
        _request.value = request
    }

    fun cancel() {
        job?.cancel()
        _state.value = State.Result("")
    }


}