package com.training.kotlincoroutines

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        lifecycleScope.launch(Dispatchers.IO) {
            fakeResponse()
        }

        Log.d("MainActivity", "Hello after launch")
    }

    suspend fun fakeResponse() {
        delay(2000)
        Log.d("MainActivity", Thread.currentThread().name.toString())
        withContext(Dispatchers.Main) {
            startActivity(Intent(this@MainActivity, SecondActivity2::class.java))
            finish()
        }
        while (true) {
        Log.d("MainActivity", "Hello after withContext")
        delay(1000)
        }
    }
}