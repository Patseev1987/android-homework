package com.example.m9_quiz_localization

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.example.m9_quiz_localization.databinding.FragmentStartBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar


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

        binding.bSetDataOfBirth.setOnClickListener {
            setDataPicker(binding.bSetDataOfBirth)
        }
    }

    private fun setDataPicker(view: View) {
        activity?.supportFragmentManager?.let {

            val dataPicker = MaterialDatePicker.Builder
                .datePicker()
                .setTitleText(R.string.set_your_data_oof_birth)
                .build()
            dataPicker.addOnPositiveButtonClickListener { timeInMillis ->
                showSnackBarWithDataOfBirth(timeInMillis, view)
            }
            dataPicker.show(it, getString(R.string.set_your_data_oof_birth))
        }
    }

    private fun showSnackBarWithDataOfBirth(timeInMillis: Long, view: View) {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeInMillis
        val dateFormat = SimpleDateFormat("dd-MM-yyyy")
        val date = calendar.time
        val dateForSnackBar = dateFormat.format(date)
        Snackbar.make(
            view,
            getString(R.string.your_date_of_birth_is, dateForSnackBar),
            Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}