package ru.bogdan.m13_databinding

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:state")
fun setState(textView: TextView, state: State) {
    when (state) {
        is State.Loading -> textView.text = "LOADING"
        is State.Result -> textView.text = state.value
        is State.Waiting -> textView.text = "Waiting"
    }
}

@BindingAdapter("android:state")
fun setState(progressBar: ProgressBar, state: State) {
    when (state) {
        is State.Loading -> progressBar.visibility = View.VISIBLE
        is State.Result -> progressBar.visibility = View.GONE
        is State.Waiting -> progressBar.visibility = View.GONE
    }
}

@BindingAdapter("android:state")
fun setState(button: Button, state: State) {
    when (state) {
        is State.Loading -> button.isEnabled = true
        is State.Result -> button.isEnabled = false
        is State.Waiting -> button.isEnabled = false
    }
}