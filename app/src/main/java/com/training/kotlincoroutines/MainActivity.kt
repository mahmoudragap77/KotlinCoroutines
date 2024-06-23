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


//        val job = lifecycleScope.launch(Dispatchers.IO) {
//            val deferred1 = async {
//                fakeResponse1()
//            }
//            val deferred2 = async {
//                fakeResponse2()
//            }
//
//
//            Log.d("MainActivity", deferred1.await())
//            Log.d("MainActivity", deferred2.await())
//
//        }
        binding.helloWorld.setOnClickListener {
            job2.cancel()
        }


            playWithJobs()

    }

    private fun playWithJobs(){
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.d("MainActivity", throwable.message.toString())
        }
        job1 =lifecycleScope.launch(exceptionHandler) {
            delay(2000)
            job2 =launch {
                delay(2000)
                Log.d("MainActivity", "job2 started")
                job4 =launch {
                    delay(2000)
                    Log.d("MainActivity", "job4 started")
                }
                job5 =launch {
                    delay(2000)
                    Log.d("MainActivity", "job5 started")
                }

            }
            job3 =launch {
                delay(2000)
                val respone =5/0
                Log.d("MainActivity", "job3 started")
            }

        }
        Log.d("MainActivity", "job1 started")
    }

    private suspend fun fakeResponse1(): String {
        delay(5000)
        return "response1"
    }

    private suspend fun fakeResponse2(): String {
        delay(3000)
        return "response2"
    }
}