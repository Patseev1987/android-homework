package com.example.m2_layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.m2_layout.databinding.ActivityMainBinding
import com.example.m2_layout.databinding.BogdanCustomViewgroupBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.customView.setLowerText("Hello fom MainActivity.kt")
        binding.customView.setUpperText("It is a long text for testing upper field.")
    }
}