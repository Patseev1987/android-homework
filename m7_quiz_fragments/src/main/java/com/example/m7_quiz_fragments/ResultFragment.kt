package com.example.m7_quiz_fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.m7_quiz_fragments.databinding.FragmentResultBinding
import com.example.m7_quiz_fragments.quiz.QuizStorage
import com.example.m7_quiz_fragments.quiz.Result


class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private lateinit var answers: Result


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        val view = binding.root
        initViews(binding)
        binding.bAgain.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_quizFragment)
        }
        return view
    }

  private fun initViews(binding: FragmentResultBinding){
      answers = ResultFragmentArgs.fromBundle(requireArguments()).results
      val numberOfRightAnswers = getNumberOfRightAnswers()
      val textTitle = if (numberOfRightAnswers != 1)
          String.format(getString(R.string.you_were_right_in_d_cases), numberOfRightAnswers)
      else String.format(getString(R.string.you_were_right_in_d_case), numberOfRightAnswers)
      setHints()
      binding.tvNumberOfRightAnswer.text = textTitle
  }

    private fun getNumberOfRightAnswers(): Int {
        var result = 0

        for (answer in answers.rightAnswers)
            if (answer == 1) {
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