package com.training.kotlincoroutines

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.training.kotlincoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var job1:Job
    lateinit var job2:Job
    lateinit var job3:Job
    lateinit var job4:Job
    lateinit var job5:Job
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playWithFlow()
    }

    private fun playWithFlow() {
        val flow = flow {
            for(i in 1..100){
                emit("Item $i")
                delay(500)
            }
        }
        lifecycleScope.launch {
            flow.collect{
                Log.d("MainActivity", it)
            }
        }

    }


}