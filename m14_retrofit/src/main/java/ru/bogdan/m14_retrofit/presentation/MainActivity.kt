package ru.bogdan.m14_retrofit.presentation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.bogdan.m14_retrofit.R
import ru.bogdan.m14_retrofit.data.ApiFactory

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val scope = CoroutineScope(Dispatchers.Main)
        val text = findViewById<TextView>(R.id.tw)


        lifecycleScope.launch {
            ApiFactory.apiService.loadUser().collect{
              Log.d("BOGDAN",it.toString())

        } }

    }
}