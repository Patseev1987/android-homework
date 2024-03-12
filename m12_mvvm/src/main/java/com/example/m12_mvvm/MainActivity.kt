package com.example.m12_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.m12_mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel:MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        viewModel.isLoading.observe(this, Observer {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            }else {
                binding.progressBar.visibility = View.GONE
            }
        })

        binding.buttonSearch.setOnClickListener {
            viewModel.loading()
        }



    }
}