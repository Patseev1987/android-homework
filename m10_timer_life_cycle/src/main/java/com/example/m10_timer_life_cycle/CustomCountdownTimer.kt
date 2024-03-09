package com.example.m10_timer_life_cycle


import kotlinx.coroutines.*


private const val SECOND = 1000L

class CustomCountdownTimer {
    private var _isStarted = false
    val isStarted get() =  _isStarted
    private val scope by lazy {

        CoroutineScope(Dispatchers.Main)
    }

    fun startCountdownTimer(
        timeDuration: Int,
        mainBlock: () -> Unit,
        afterMainBlock: (() -> Unit)? = null,
        beforeMainBlock: (() -> Unit)? = null
    ) {
        changeIsStarted()
        scope.launch {
            beforeMainBlock?.invoke()
            for (i in 1..timeDuration) {
                if (!isStarted) {
                    break
                }
                mainBlock.invoke()
                delay(SECOND)
            }
            afterMainBlock?.invoke()
            stopCountdownTimer()
        }
    }


    fun cancel() {
        scope.cancel()
    }

    fun stopCountdownTimer() {
        _isStarted = false
    }

    private fun changeIsStarted(){
        _isStarted = !_isStarted
    }
}