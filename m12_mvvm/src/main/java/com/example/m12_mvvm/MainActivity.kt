package com.example.m12_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.m12_mvvm.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        binding.buttonSearch.isEnabled = false
        observe(viewModel = viewModel, binding = binding)
        setListeners(viewModel = viewModel, binding = binding)
    }


    private fun setListeners(binding: ActivityMainBinding, viewModel: MainActivityViewModel) {
        binding.buttonSearch.setOnClickListener {
            viewModel.loading(binding.inputEditText.text.toString())
        }
        binding.inputEditText.doAfterTextChanged {
            viewModel.setNumberOfLettersInRequest(binding.inputEditText.length())
        }
    }


    private fun observe(binding: ActivityMainBinding, viewModel: MainActivityViewModel) {
        viewModel.state.observe(this) {
            binding.progressBar.visibility = View.GONE
            binding.buttonSearch.isEnabled = true
            binding.inputEditText.isEnabled = true
            when (it) {
                is Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.buttonSearch.isEnabled = false
                    binding.inputEditText.isEnabled = false
                }

                is Waiting -> {
                    binding.buttonSearch.isEnabled = false
                }

                is Ready -> {
                    binding.buttonSearch.isEnabled = true
                }

                is Result -> {
                    binding.results.text = it.value
                }
            }
        }
    }
}