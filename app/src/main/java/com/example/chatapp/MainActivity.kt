package com.example.chatapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapp.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        val sharedPreferences = getSharedPreferences("MyCache", Context.MODE_PRIVATE)
//        val cachedValue = sharedPreferences.getString("key", "")
//        if (cachedValue=="VerfiyingActivitiy"){
//            val intent = Intent(this,VerfiyingActivitiy::class.java)
//            startActivity(intent)
//        }
//        if (cachedValue=="OTPActivity"){
//            val intent = Intent(this,OTPActivity::class.java)
//            startActivity(intent)
//        }
        binding.btnGetStarted.setOnClickListener {
            val intent = Intent(this,VerfiyingActivitiy::class.java)
            startActivity(intent)
        }


    }
}


