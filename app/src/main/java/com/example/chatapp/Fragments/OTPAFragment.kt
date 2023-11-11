package com.example.chatapp.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.ControlledComposition
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivityMainBinding
import com.example.chatapp.databinding.FragmentOTPABinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OTPAFragment : Fragment() {
    private val binding by lazy { FragmentOTPABinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val sharedPreferences = requireContext().getSharedPreferences("MyCache", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("key", "OTPActivity")
        editor.apply()


        val bundle = Bundle()
        val phoneNumber=bundle.getString("phoneNumber")
        binding.thisphone.text=phoneNumber
        GlobalScope.launch {
            delay(1000)
            for (i in 60 downTo 1) {
                withContext(Dispatchers.Main) {
                    binding.tvTimer.text = i.toString()
                }

            }
        }

        return binding.root
    }


}