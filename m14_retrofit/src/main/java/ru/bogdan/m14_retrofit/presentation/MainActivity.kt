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
import ru.bogdan.m14_retrofit.di.DaggerComponent
import ru.bogdan.m14_retrofit.domain.GetUserUseCase
import ru.bogdan.m14_retrofit.domain.UpdateUserUseCase
import javax.inject.Inject

class MainActivity : AppCompatActivity() {


    private val component by lazy {
        DaggerComponent.create()
    }
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        component.inject(this)
        observeFlow(binding, viewModel)
        setListeners(binding,viewModel)

    }

    private fun observeFlow(binding: ActivityMainBinding, viewModel: MainViewModel) {
        lifecycleScope
            .launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    viewModel.state.collectLatest {
                        when (it) {
                            is State.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                                binding.bUpdate.isEnabled = false
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

                                binding.bUpdate.isEnabled = true

                            }
                        }
                    }
                }
            }
    }

    private fun setListeners(binding: ActivityMainBinding, viewModel: MainViewModel) {

        binding.bUpdate.setOnClickListener {
            viewModel.updateData()
        }
    }
}