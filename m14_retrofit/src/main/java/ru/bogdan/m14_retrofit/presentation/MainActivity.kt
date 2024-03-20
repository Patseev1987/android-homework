package ru.bogdan.m14_retrofit.presentation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.*
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.bogdan.m14_retrofit.R
import ru.bogdan.m14_retrofit.data.ApiFactory
import ru.bogdan.m14_retrofit.data.ApiHelperImpl
import ru.bogdan.m14_retrofit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val apiHelperImpl = ApiHelperImpl()

    private val viewModelFactory = ViewModelFactory(apiHelperImpl)

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeFlow(binding, viewModel)
        setListeners(binding)

    }

    private fun observeFlow(binding: ActivityMainBinding, viewModel: MainViewModel) {
        lifecycleScope
            .launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    viewModel.state.collectLatest {
                        when (it) {
                            is State.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                            }

                            is State.Start -> {
                                binding.progressBar.visibility = View.GONE
                            }

                            is State.Result -> {
                                binding.progressBar.visibility = View.GONE
                                binding.twFirstName.text = it.user.name
                                binding.twLastName.text = it.user.secondName
                                binding.twAge.text = it.user.age
                                binding.twEmail.text = it.user.email
                                binding.twPhone.text = it.user.phone
                                binding.twCity.text = it.user.city
                                binding.twCountry.text = it.user.country
                                binding.twNationality.text = it.user.nationality

                                Glide.with(this@MainActivity)
                                    .load(it.user.imageUrl)
                                    .into(binding.iwPhoto)

                            }
                        }
                    }
                }
            }
    }

    private fun setListeners(binding: ActivityMainBinding) {

        binding.bUpdate.setOnClickListener {
            viewModel.updateData()
        }
    }
}