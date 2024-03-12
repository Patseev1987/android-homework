package com.example.m12_mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivityViewModel:ViewModel() {

    private var _isLoading:MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading
    private var results = listOf("We don't have any results!", "Not found!", "Connection error!")
    private var _result:

    private val scope = viewModelScope
    fun loading(){
        scope.launch {
            _isLoading.value = true
            delay(5000)

        }

    }
}