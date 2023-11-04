package com.example.chatapp

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.annotation.MainThread
import com.example.chatapp.databinding.ActivityOtpactivityBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread

class OTPActivity : AppCompatActivity() {
    private val binding by lazy { ActivityOtpactivityBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val sharedPreferences = getSharedPreferences("MyCache", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("key", "OTPActivity")
        editor.apply()

        val phoneNumber=intent.getStringExtra("phoneNumber")
        binding.thisphone.text=phoneNumber
        GlobalScope.launch {
            for (i in 60 downTo 1) {
                withContext(Dispatchers.Main) {
                    binding.tvTimer.text = i.toString()
                }
                delay(1000)
            }
        }

    }
}