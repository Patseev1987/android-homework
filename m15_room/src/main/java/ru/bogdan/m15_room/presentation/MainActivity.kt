package ru.bogdan.m15_room.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import kotlinx.coroutines.launch
import ru.bogdan.m15_room.data.ApplicationRepositoryImpl
import ru.bogdan.m15_room.data.WordMapper
import ru.bogdan.m15_room.data.database.WordsDatabase
import ru.bogdan.m15_room.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val component by lazy {
        (application as WordApplication).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        component.inject(this)
        val adapter = WordAdapter()
        binding.rwWords.adapter = adapter
        setOnClickListeners(binding, viewModel)
        observeData(binding, viewModel, adapter)
    }


    private fun setOnClickListeners(binding: ActivityMainBinding, viewModel: MainViewModel) {
        binding.bAdd.setOnClickListener {
            viewModel.saveWord(binding.inputEditText.text.toString())
        }

        binding.bClearDatabase.setOnClickListener {
            viewModel.clearDatabase()
        }
    }

    private fun observeData(binding: ActivityMainBinding, viewModel: MainViewModel, adapter: WordAdapter) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.state.collect {

                    when (it) {
                        is State.Error -> {
                            binding.inputLayout.error = it.msg
                        }
                        is State.Result -> {
                            adapter.submitList(it.words)
                            binding.inputLayout.error = ""
                        }
                    }
                }
            }
        }
    }
}