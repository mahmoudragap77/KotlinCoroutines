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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playWithFlow()
    }

    private fun playWithFlow() {
        val flow = flow { //this function is called when the flow is started
            for (i in 1..8) {
                if (i == 5) throw Exception("Error")
                emit("Item $i : on {Thread ${Thread.currentThread().name}}")  //this function is called when the flow emits a value
                delay(500)
            }
        }.flowOn(Dispatchers.Default)  //this function is called when the flow is started on a different thread

        lifecycleScope.launch {
            flow.onCompletion {  //this function is called when the flow is completed
                Log.d("MainActivity", "Flow completed")
            }.catch {   //this function is called when the flow throws an exception
                Log.d("MainActivity", "Flow error")
            }.collect {  //this function is called when the flow emits a value
                Log.d("MainActivity", "$it on {Thread ${Thread.currentThread().name}}")
            }
        }

    }


}