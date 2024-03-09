package com.example.m10_timer_life_cycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import androidx.lifecycle.lifecycleScope
import com.example.m10_timer_life_cycle.databinding.ActivityMainBinding
import kotlinx.coroutines.*

const val IS_STARTED_KEY = "is started"
const val COUNTER_KEY = "counter"
const val PROGRESS_INDICATOR_KEY = "progress"
const val START_CONDITION_PROGRESS_INDICATOR_VALUE = 60
const val START_CONDITION_COUNTER = 10
const val START_CONDITION_IS_STARTED = false


class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val timer by lazy {
        CustomCountdownTimer()
    }

    private var counter = START_CONDITION_COUNTER
    private var progressIndicatorValue = START_CONDITION_PROGRESS_INDICATOR_VALUE
    private var isStarted = START_CONDITION_IS_STARTED


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setListener(binding)
        if (savedInstanceState != null) {
            setStartConditions(savedInstanceState)
            initView(binding)
            if (isStarted) {
                startCountdown(counter, binding)
            }
        }
    }

    private fun initView(binding: ActivityMainBinding) {
        binding.tvCounter.text = counter.toString()
        binding.progressIndicator.progress = counter
        if (isStarted){
            binding.bStartStop.text = getString(R.string.stop)
        }else  {
            binding.bStartStop.text = getString(R.string.start)
        }
    }

    private fun setStartConditions(savedInstanceState: Bundle) {
        counter = savedInstanceState.getInt(COUNTER_KEY)
        progressIndicatorValue = savedInstanceState.getInt(PROGRESS_INDICATOR_KEY)
        isStarted = savedInstanceState.getBoolean(IS_STARTED_KEY)
    }


    private fun setListener(binding: ActivityMainBinding) {

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                seekBar?.progress?.let {
                    counter = getTimeInSecondsFromSeekBar(it)
                    binding.tvCounter.text = counter.toString()
                    binding.progressIndicator.progress = counter
                }
            }
        })




        binding.bStartStop.setOnClickListener {
            if (!timer.isStarted) {
                startCountdown(counter, binding)
                changeButtonText()
            } else {
                stopCountDownTimer()
            }

        }
    }

    private fun startCountdown(time: Int, binding: ActivityMainBinding) {
        var innerCounter = time
        binding.progressIndicator.progress = counter
        timer.startCountdownTimer(time,
            mainBlock = {
                innerCounter -= 1
                counter = innerCounter
                binding.tvCounter.text = counter.toString()
                binding.progressIndicator.setProgress(counter, true)
                progressIndicatorValue = counter
            },
            afterMainBlock = {
                isStarted = timer.isStarted
                binding.seekBar.isEnabled = true
                changeButtonText()
            },
            beforeMainBlock = {
                isStarted = timer.isStarted
                binding.seekBar.isEnabled = false
            })

    }

    private fun stopCountDownTimer() {
        timer.stopCountdownTimer()
        isStarted = timer.isStarted
    }

    private fun getTimeInSecondsFromSeekBar(value: Int): Int = when (value) {
        0 -> 10
        1 -> 20
        2 -> 30
        3 -> 40
        4 -> 50
        5 -> 60
        else -> -1
    }

    private fun changeButtonText() {
        if (binding.bStartStop.text == getString(R.string.start)) {
            binding.bStartStop.text = getString(R.string.stop)
        } else {
            binding.bStartStop.text = getString(R.string.start)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(IS_STARTED_KEY, isStarted)
        outState.putInt(COUNTER_KEY, counter)
        outState.putInt(PROGRESS_INDICATOR_KEY, progressIndicatorValue)
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}