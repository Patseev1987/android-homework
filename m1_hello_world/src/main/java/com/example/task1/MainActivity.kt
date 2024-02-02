package com.example.task1

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.core.view.isVisible
import com.example.task1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var counter: Int = 0

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.numberOfPassengers.text = counter.toString()

        binding.reset.setOnClickListener {
            bussIsEmpty()
        }

        binding.increment.setOnClickListener {
            counter++
            binding.numberOfPassengers.text = counter.toString()
            if (counter > 0) binding.decrement.isEnabled = true
            if (counter > 49) {
                bussIsFull()
            } else if (counter in 1..49) {
                bussNotEmptyAndNotFull()
            }
        }

        binding.decrement.setOnClickListener {
            counter--
            binding.numberOfPassengers.text = counter.toString()
            if (counter in 1..49) {
                bussNotEmptyAndNotFull()
            } else if (counter > 49) {
                bussIsFull()
            } else {
                bussIsEmpty()
            }
        }
    }


    @SuppressLint("ResourceAsColor")
    private fun bussIsEmpty() {
        counter = 0
        binding.numberOfPassengers.text = counter.toString()
        binding.reset.isVisible = false
        binding.decrement.isEnabled = false
        binding.informationAboutSeats.text = getString(R.string.emptyBuss)
        binding.informationAboutSeats.setTextColor(getColor(R.color.green))
    }

    private fun bussIsFull() {
        binding.reset.isVisible = true
        binding.informationAboutSeats.text = getString(R.string.aLotOfPassenger)
        binding.informationAboutSeats.setTextColor(getColor(R.color.red))
    }

    private fun bussNotEmptyAndNotFull() {
        binding.informationAboutSeats.text = "${getString(R.string.notEmptyNotFull)} $counter"
        binding.informationAboutSeats.setTextColor(getColor(R.color.blue))
    }
}