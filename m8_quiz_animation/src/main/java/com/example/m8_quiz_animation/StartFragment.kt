package com.example.m8_quiz_animation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.m8_quiz_animation.databinding.FragmentStartBinding


class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners(binding)
    }

    private fun setListeners(binding: FragmentStartBinding) {
        binding.bLetsGo.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_quizFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}