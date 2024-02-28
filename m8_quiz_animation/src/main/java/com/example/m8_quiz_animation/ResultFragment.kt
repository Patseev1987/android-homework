package com.example.m8_quiz_animation


import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.navigation.fragment.findNavController
import com.example.m8_quiz_animation.databinding.FragmentResultBinding
import com.example.m8_quiz_animation.quiz.QuizStorage
import com.example.m8_quiz_animation.quiz.Result


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
        setText()
        setOnClickListener()
        setAnimation()
        return view
    }


    private fun setAnimation() {
        ObjectAnimator.ofFloat(
            binding.bAgain, View.ROTATION, 0f, 360f
        ).apply {
            duration = 3000
            interpolator = AccelerateInterpolator()
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            start()
        }
        ObjectAnimator.ofFloat(
            binding.tvNumberOfRightAnswer, View.ROTATION, -10f, 10f
        ).apply {
            duration = 2000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            start()
        }

        ObjectAnimator.ofFloat(
            binding.bAgain, View.SCALE_X, 0.5f, 2f
        ).apply {
            duration = 3000
            interpolator = AccelerateInterpolator()
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            start()
        }

        ObjectAnimator.ofFloat(
            binding.bAgain, View.SCALE_Y, 0.5f, 2f
        ).apply {
            duration = 3000
            interpolator = AccelerateInterpolator()
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            start()
        }
    }

    private fun setOnClickListener() {
        binding.bAgain.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_quizFragment)
        }
    }

    private fun setText() {
        answers = ResultFragmentArgs.fromBundle(requireArguments()).results
        val numberOfRightAnswers = getNumberOfRightAnswers()
        val textTitle = if (numberOfRightAnswers != 1) String.format(
            getString(R.string.you_were_right_in_d_cases),
            numberOfRightAnswers
        )
        else String.format(getString(R.string.you_were_right_in_d_case), numberOfRightAnswers)
        setHints()
        binding.tvNumberOfRightAnswer.text = textTitle
    }

    private fun getNumberOfRightAnswers(): Int {
        var result = 0

        for (answer in answers.rightAnswers) if (answer == 1) {
            result++
        }
        return result
    }

    private fun setHints() {
        if (answers.rightAnswers.contains(0)) {
            val sb = StringBuilder()
            for ((i, answer) in answers.rightAnswers.withIndex()) {
                if (answer == 0) {
                    sb.append("In ${i + 1} case the right answer is:\n${QuizStorage.questions[i].rightAnswer}\n")
                }
            }
            binding.hints.text = sb.toString()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}