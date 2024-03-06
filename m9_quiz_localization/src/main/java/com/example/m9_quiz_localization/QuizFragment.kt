package com.example.m9_quiz_localization

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.m9_quiz_localization.quiz.Result
import androidx.navigation.fragment.findNavController
import com.example.m9_quiz_localization.custom.CustomView
import com.example.m9_quiz_localization.databinding.FragmentQuizBinding
import com.example.m9_quiz_localization.quiz.QuizStorage


class QuizFragment : Fragment() {

    private val quizStorage = QuizStorage
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(binding)
        setOnClickListeners(binding)
        setAnimation(binding)
    }


    private fun setOnClickListeners(binding: FragmentQuizBinding) {
        binding.bBack.setOnClickListener {
            findNavController().navigate(R.id.action_quizFragment_to_startFragment)
        }
        binding.bSent.setOnClickListener {
            if (checkRadioGroups()) {
                moveToQuizFragment()
            }
        }
    }

    private fun moveToQuizFragment() {
        val result = getResults()
        val action = QuizFragmentDirections
            .actionQuizFragmentToResultFragment(result)
        findNavController().navigate(action)
    }


    private fun getResults(): Result {
        val result = IntArray(3)

        val answer1 = binding.cv1.getAnswer()
        val answer2 = binding.cv2.getAnswer()
        val answer3 = binding.cv3.getAnswer()

        if (answer1 == resources.getStringArray(R.array.answers_array)[0]) {
            result[0] = 1
        } else {
            result[0] = 0
        }

        if (answer2 == resources.getStringArray(R.array.answers_array)[3]) {
            result[1] = 1
        } else {
            result[1] = 0
        }
        if (answer3 == resources.getStringArray(R.array.answers_array)[6]) {
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

    private fun initView(binding: FragmentQuizBinding) {

        val questions = resources.getStringArray(R.array.question_arrays)

        binding.cv1.setTextForQuestion(questions[0])
        binding.cv2.setTextForQuestion(questions[1])
        binding.cv3.setTextForQuestion(questions[2])

        setAnswers(binding.cv1)
        setAnswers(binding.cv2)
        setAnswers(binding.cv3)

    }


    private fun setAnswers(cv: CustomView) {

        when (cv) {
            binding.cv1 -> {
              val answers =  listOf(
                  resources.getStringArray(R.array.answers_array)[0],
                  resources.getStringArray(R.array.answers_array)[1],
                  resources.getStringArray(R.array.answers_array)[2]
              )
                cv.setTextForAnswer(answers)
            }
            binding.cv2 -> {
                val answers =  listOf(
                    resources.getStringArray(R.array.answers_array)[3],
                    resources.getStringArray(R.array.answers_array)[4],
                    resources.getStringArray(R.array.answers_array)[5]
                )
                cv.setTextForAnswer(answers)
            }
            else -> {
                val answers =  listOf(
                    resources.getStringArray(R.array.answers_array)[6],
                    resources.getStringArray(R.array.answers_array)[7],
                    resources.getStringArray(R.array.answers_array)[8]
                )
                cv.setTextForAnswer(answers)
            }
        }




    }

    private fun setAnimation(binding: FragmentQuizBinding) {
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
