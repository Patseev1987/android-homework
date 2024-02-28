package com.example.m8_quiz_animation.custom

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.m8_quiz_animation.R
import com.example.m8_quiz_animation.databinding.QuestionLayoutBinding

import java.util.*

class CustomView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private val binding: QuestionLayoutBinding
    private var answer: String = UNKNOWN_ANSWER

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

    fun checkRadioButtonGroup(): Boolean {
        return when (binding.rgAnswers.checkedRadioButtonId) {
            R.id.rb_answer1 -> true
            R.id.rb_answer2 -> true
            R.id.rb_answer3 -> true
            else -> false
        }
    }

    fun getAnswer(): String {
        answer = when {
            binding.rbAnswer1.isChecked -> binding.rbAnswer1.text.toString()
            binding.rbAnswer2.isChecked -> binding.rbAnswer2.text.toString()
            binding.rbAnswer3.isChecked -> binding.rbAnswer3.text.toString()
            else -> UNKNOWN_ANSWER
        }

        return answer
    }


    companion object {
        const val UNKNOWN_ANSWER = ""
    }

}