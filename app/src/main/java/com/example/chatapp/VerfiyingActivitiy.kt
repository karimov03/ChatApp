package com.example.chatapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapp.databinding.ActivityVerfiyingActivitiyBinding
import java.util.regex.Pattern

class VerfiyingActivitiy : AppCompatActivity() {
    private val binding by lazy { ActivityVerfiyingActivitiyBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val sharedPreferences = getSharedPreferences("MyCache", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("key", "VerfiyingActivitiy")
        editor.apply()

        binding.edtPhoneNumber.text.clear()
        binding.btnGetStarted.setOnClickListener {
            val phone = binding.edtPhoneNumber.text.toString()
            var text = ""
            val pattern: Pattern = Pattern.compile(" ")
            val words: Array<String> = pattern.split(phone)
            for (word in words) {
                text+=word
            }
            if (text!="" && text.length==9){
                text="+998"+text
                val intent = Intent(this@VerfiyingActivitiy,OTPActivity::class.java)
                intent.putExtra("phoneNumber",text)
                startActivity(intent)
            }
            else{
                binding.edtPhoneNumber.requestFocus()

                binding.thisphone.setTextColor(Color.parseColor("#FFF20606"))
                val thread=Thread{
                    Thread.sleep(500)
                    binding.thisphone.setTextColor(Color.parseColor("#FFFFFF"))

                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.showSoftInput(binding.edtPhoneNumber, InputMethodManager.SHOW_IMPLICIT)
                }
                thread.start()
            }

        }

    }


}