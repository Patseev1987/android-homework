package ru.bogdan.m11_timer_data_storage.presentation

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.bogdan.m11_timer_data_storage.data.RepositoryImpl
import ru.bogdan.m11_timer_data_storage.databinding.FragmentStartBinding
import ru.bogdan.m11_timer_data_storage.domain.*


class StartFragment : Fragment() {

    private val repository by lazy { RepositoryImpl(requireContext()) }
    private val getTextUseCase by lazy{GetTextUseCase(repository)}
    private val saveTextUseCase by lazy{ SaveTextUseCase(repository)}
    private val clearTextUseCase by lazy{ ClearTextUseCase(repository)}
    private var _binding:FragmentStartBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(binding)
        setOnClickListeners(binding)
    }

   private fun initView(binding: FragmentStartBinding){
       binding.tvStartFragment .text = getTextUseCase.getText()
   }

    private fun setOnClickListeners(binding: FragmentStartBinding){
        binding.bClear.setOnClickListener {
            clearTextUseCase.clearText()
            initView(binding)
        }

        binding.bSave.setOnClickListener {
            val text = binding.etStartFragment.text.toString()
            saveTextUseCase.saveText(text)
            initView(binding)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}