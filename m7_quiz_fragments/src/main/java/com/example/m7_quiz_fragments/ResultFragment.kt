package com.example.m7_quiz_fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.m7_quiz_fragments.databinding.FragmentResultBinding
import com.example.m7_quiz_fragments.quiz.Result


class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private lateinit var answers: Result


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {


        _binding = FragmentResultBinding.inflate(inflater, container, false)
        val view = binding.root

        answers = ResultFragmentArgs.fromBundle(requireArguments()).results
        val numberOfRightAnswers = getNumberOfRightAnswers()
        binding.tvNumberOfRightAnswer.text =
            String.format(getString(R.string.you_were_right_in_d_cases), numberOfRightAnswers)
        binding.bAgain.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_quizFragment)
        }
        return view
    }

    private fun getNumberOfRightAnswers(): Int {
        var result = 0

        for (answer in answers.rightAnswers)
            if (answer == 1) {
                result++
            }
        return result
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}