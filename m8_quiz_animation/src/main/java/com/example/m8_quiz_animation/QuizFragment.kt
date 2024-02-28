package com.example.m8_quiz_animation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.FloatRange
import androidx.navigation.fragment.findNavController
import com.example.m8_quiz_animation.custom.CustomView
import com.example.m8_quiz_animation.databinding.FragmentQuizBinding
import com.example.m8_quiz_animation.quiz.QuizStorage
import com.example.m8_quiz_animation.quiz.Result


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
        setListeners()
        setAnimation()
        return view
    }

    private fun setListeners() {
        binding.bBack.setOnClickListener {
            findNavController().navigate(R.id.action_quizFragment_to_startFragment)
        }
        binding.bSent.setOnClickListener {
            if (checkRadioGroups()) {
                val result = getResults()
                val action = QuizFragmentDirections
                    .actionQuizFragmentToResultFragment(result)
                findNavController().navigate(action)
            }
        }
    }

    private fun getResults(): Result {
        val result = IntArray(3)

        val answer1 = binding.cv1.getAnswer()
        val answer2 = binding.cv2.getAnswer()
        val answer3 = binding.cv3.getAnswer()

        if (answer1 == quizStorage.questions[0].rightAnswer) {
            result[0] = 1
        } else {
            result[0] = 0
        }

        if (answer2 == quizStorage.questions[1].rightAnswer) {
            result[1] = 1
        } else {
            result[1] = 0
        }
        if (answer3 == quizStorage.questions[2].rightAnswer) {
            result[2] = 1
        } else {
            result[2] = 0
        }
        return Result(result)
    }

    private fun checkRadioGroups(): Boolean {
        if (!binding.cv1.checkRadioButtonGroup()) {
            Toast.makeText(
                this.context,
                getString(R.string.you_should_choose_answer_on_the_first_question), Toast.LENGTH_SHORT
            ).show()
            return false
        }
        if (!binding.cv2.checkRadioButtonGroup()) {
            Toast.makeText(
                this.context,
                getString(R.string.you_should_choose_answer_on_the_second_question), Toast.LENGTH_SHORT
            ).show()
            return false
        }
        if (!binding.cv3.checkRadioButtonGroup()) {
            Toast.makeText(
                this.context,
                getString(R.string.you_should_choose_answer_on_the_third_question), Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
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

    private fun setAnimation() {
        binding.bBack.animate().apply {
            duration = 5000
            alpha(1f)
        }.start()
        binding.cv2.scaleX = 0.1f
        binding.cv2.scaleY = 0.1f
        binding.cv2.animate().apply {
            duration = 2000
            rotation(360f)
            scaleX(1f)
            scaleY(1f)
        }.start()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}