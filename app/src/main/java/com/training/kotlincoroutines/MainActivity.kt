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

        var firstResponse: String? = null
        var secondResponse: String? = null
        val job = lifecycleScope.launch(Dispatchers.IO) {
            val job1 = launch {
                fakeResponse1()
            }
            val job2 = launch {
                fakeResponse2()
            }

            job1.join()   // wait for job1 to finish and then move on
            Log.d("MainActivity", firstResponse.toString())
            Log.d("MainActivity", secondResponse.toString())

        }
        binding.helloWorld.setOnClickListener {
            job.cancel()
        }

    }

    suspend fun fakeResponse1(): String {
        delay(5000)
        return "response1"
    }

    suspend fun fakeResponse2(): String {
        delay(3000)
        return "response2"
    }
}