package com.example.m16_architecture.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.m16_architecture.R
import com.example.m16_architecture.databinding.ActivityMainBinding
import com.example.m16_architecture.di.DaggerApplicationComponent
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private val component by lazy {
        DaggerApplicationComponent.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        component.inject(this)
        setOnClickListeners(binding,viewModel)
        observeViewModel(binding,viewModel)
    }

    private fun setOnClickListeners(binding: ActivityMainBinding, viewModel: MainViewModel) {
        binding.bRefresh.setOnClickListener {
            viewModel.updateData()
        }
    }

    private fun observeViewModel(binding: ActivityMainBinding, viewModel: MainViewModel) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.state.collect {
                    when (it) {
                        is State.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.bRefresh.isEnabled = false
                        }

                        is State.Result -> {
                            binding.progressBar.visibility = View.GONE
                            binding.bRefresh.isEnabled = true
                            binding.twType.text =
                                it.activity.type.replaceFirstChar { it.uppercase()}
                            binding.twActivity.text = it.activity.activity
                        }
                    }
                }
            }
        }

    }
}