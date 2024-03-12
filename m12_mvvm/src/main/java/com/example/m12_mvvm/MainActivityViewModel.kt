package com.example.m12_mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivityViewModel : ViewModel() {

    private var _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading
    private var request:String = ""
    private var results = listOf("""We couldn't find anything about "%s"""", """"%s" - Not found!""", "Connection error!")
    private var _result: MutableLiveData<String> = MutableLiveData()
    val result: LiveData<String> get() = _result
    private var _length: MutableLiveData<Int> = MutableLiveData()
    val length: LiveData<Int> get() = _length


    private val scope = viewModelScope
    fun loading() {
        scope.launch {
            _isLoading.value = true
            delay(5000)
            _result.value =  String.format( results[Random.nextInt(results.size)],request)
            _isLoading.value = false
        }
    }

    fun setRequest(request:String){
        this.request = request
    }
    fun setLength(length: Int) {
        _length.value = length
    }
}