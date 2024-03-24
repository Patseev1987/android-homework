package ru.bogdan.m15_room.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import kotlinx.coroutines.launch
import ru.bogdan.m15_room.data.ApplicationRepositoryImpl
import ru.bogdan.m15_room.data.database.WordsDatabase
import ru.bogdan.m15_room.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

         val database = WordsDatabase.getInstance(this.application)
         val applicationRepositoryImpl = ApplicationRepositoryImpl(database.wordDao,lifecycleScope)

         val viewModelFactory = ViewModelFactory(applicationRepositoryImpl)

         val viewModel =
            ViewModelProvider(this,viewModelFactory)[MainViewModel::class.java]


        val adapter = WordAdapter()
        binding.rwWords.adapter = adapter



        binding.bAdd.setOnClickListener {
            viewModel.saveWord(binding.inputEditText.text.toString())
        }

        binding.bClearDatabase.setOnClickListener {
            viewModel.clearDatabase()
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.state.collect{
                    adapter.submitList(it)


                }
            }
        }


    }
}