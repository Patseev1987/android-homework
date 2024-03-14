package com.example.m12_mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.m12_mvvm.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivityViewModel : ViewModel() {

    private var _state:MutableLiveData<State> = MutableLiveData()
    val state:LiveData<State> get() = _state

    private var results = listOf(
        """We couldn't find anything about "%s""""
        , """"%s" - Not found!"""
        , "Connection error!"
    )



    private val scope = viewModelScope
    fun loading(request: String) {
        scope.launch {
            _state.value = Loading
            delay(5000)
            val result =  String.format(
                results[Random.nextInt(results.size)]
                ,request
            )
            _state.value = Result(result)
        }
    }

    fun setNumberOfLettersInRequest(value:Int){
     _state.value = if (value<3) Waiting else Ready
    }

}