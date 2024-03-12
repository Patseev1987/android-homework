package com.example.m12_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.m12_mvvm.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.setLength(binding.inputEditText.length())
        observe(viewModel = viewModel, binding = binding)
        setListeners(viewModel = viewModel, binding = binding)
    }


    private fun setListeners(binding: ActivityMainBinding, viewModel: MainActivityViewModel) {
        binding.buttonSearch.setOnClickListener {
            viewModel.loading()
            viewModel.setRequest(binding.inputEditText.text.toString())
        }
        binding.inputEditText.doAfterTextChanged {
            viewModel.setLength(binding.inputEditText.length())
        }
    }


    private fun observe(binding: ActivityMainBinding, viewModel: MainActivityViewModel) {
        viewModel.result.observe(this, Observer {
            binding.results.text = it
        })
        viewModel.isLoading.observe(this, Observer {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
                binding.buttonSearch.isEnabled = false
                binding.inputEditText.isEnabled = false
            } else {
                binding.progressBar.visibility = View.GONE
                binding.buttonSearch.isEnabled = true
                binding.inputEditText.isEnabled = true
            }
        })

        viewModel.length.observe(this, Observer {
            binding.buttonSearch.isEnabled = it > 3
        })
    }
}