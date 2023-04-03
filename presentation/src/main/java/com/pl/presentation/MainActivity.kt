package com.pl.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pl.presentation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}