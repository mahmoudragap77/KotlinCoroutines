package com.training.kotlincoroutines

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.training.kotlincoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val job =lifecycleScope.launch(Dispatchers.IO) {
            launch {
                fakeResponse1()
            }
            launch {
                fakeResponse2()
            }

        }
            binding.helloWorld.setOnClickListener {
                job.cancel()
            }

    }

    suspend fun fakeResponse1() {
       delay(5000)
        Log.d("MainActivity", "fakeResponse: response1 ")
    }
    suspend fun fakeResponse2() {
       delay(3000)
        Log.d("MainActivity", "fakeResponse: response2 ")
    }
}