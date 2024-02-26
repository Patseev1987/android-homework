package com.example.m7_quiz_fragments.custom

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.m7_quiz_fragments.R
import com.example.m7_quiz_fragments.databinding.QuestionLayoutBinding
import java.util.*

class CustomView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private val binding: QuestionLayoutBinding

    init {
        val view = inflate(context, R.layout.question_layout, this)
        binding = QuestionLayoutBinding.bind(view)
    }

    fun setTextForQuestion(question: String) {
        binding.tvQuestion.text = question
    }

    fun setTextForAnswer(answers: List<String>) {
        val random = Random().nextInt(6)

        when (random) {
            1 -> {
                binding.rbAnswer1.text = answers[0]
                binding.rbAnswer2.text = answers[1]
                binding.rbAnswer3.text = answers[2]
            }

            2 -> {
                binding.rbAnswer1.text = answers[1]
                binding.rbAnswer2.text = answers[2]
                binding.rbAnswer3.text = answers[0]
            }

            3 -> {
                binding.rbAnswer1.text = answers[2]
                binding.rbAnswer2.text = answers[0]
                binding.rbAnswer3.text = answers[1]
            }

            0 -> {
                binding.rbAnswer1.text = answers[2]
                binding.rbAnswer2.text = answers[1]
                binding.rbAnswer3.text = answers[0]
            }

            4 -> {
                binding.rbAnswer1.text = answers[0]
                binding.rbAnswer2.text = answers[2]
                binding.rbAnswer3.text = answers[1]
            }

            5 -> {
                binding.rbAnswer1.text = answers[1]
                binding.rbAnswer2.text = answers[0]
                binding.rbAnswer3.text = answers[2]
            }

        }

    }

}