package ru.bogdan.m13_databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.bogdan.m13_databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MainActivityViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.inputEditText.doAfterTextChanged {
            viewModel.setRequest(binding.inputEditText.text.toString())
        }

        binding.buttonCancel.setOnClickListener {
            viewModel.cancel()
        }

        observeFlow(binding,viewModel)
    }


    private fun observeFlow(binding: ActivityMainBinding, viewModel: MainActivityViewModel){
       lifecycleScope.launch {
           viewModel.state
               .collect{
                when (it) {
                    is State.Waiting -> {

                    }
                    is State.Ready -> {
                        binding.progressBar.visibility = View.GONE
                        binding.buttonCancel.isEnabled = false
                    }
                    is State.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.buttonCancel.isEnabled = true
                    }
                    is State.Result -> {
                        binding.progressBar.visibility = View.GONE
                        binding.buttonCancel.isEnabled = false
                        binding.results.text = it.value
                    }
                }
       } }

    }
}