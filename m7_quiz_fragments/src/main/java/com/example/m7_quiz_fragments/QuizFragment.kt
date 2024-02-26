package com.example.m7_quiz_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.m7_quiz_fragments.custom.CustomView
import com.example.m7_quiz_fragments.databinding.FragmentQuizBinding
import com.example.m7_quiz_fragments.quiz.QuizStorage
import java.util.Random


class QuizFragment : Fragment() {

    private val quizStorage = QuizStorage
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        val view = binding.root
        initView()
        binding.bBack.setOnClickListener {
            findNavController().navigate(R.id.action_quizFragment_to_startFragment)
        }
        binding.bSent.setOnClickListener {
            findNavController().navigate(R.id.action_quizFragment_to_resultFragment)
        }
        return view
    }


    private fun initView() {


        binding.cv1.setTextForQuestion(quizStorage.questions[0].question)
        binding.cv2.setTextForQuestion(quizStorage.questions[1].question)
        binding.cv3.setTextForQuestion(quizStorage.questions[2].question)

        setAnswers(binding.cv1)
        setAnswers(binding.cv2)
        setAnswers(binding.cv3)

    }

    private fun setAnswers(cv: CustomView) {

        val questionNumber = when (cv) {
            binding.cv1 -> 0
            binding.cv2 -> 1
            else -> 2
        }
        val answers = listOf(
            quizStorage.questions[questionNumber].rightAnswer,
            quizStorage.questions[questionNumber].wrongAnswer1,
            quizStorage.questions[questionNumber].wrongAnswer2
        )
        cv.setTextForAnswer(answers)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}