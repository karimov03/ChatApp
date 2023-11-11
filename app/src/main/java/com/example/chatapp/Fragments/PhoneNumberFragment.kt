package com.example.chatapp.Fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.compose.runtime.ReusableContent
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentPhoneNumberBinding
import com.google.firebase.database.core.Context
import java.util.regex.Pattern

class PhoneNumberFragment : Fragment() {
    private val binding by lazy { FragmentPhoneNumberBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding.edtPhoneNumber.text.clear()
        binding.btnGetStarted.setOnClickListener {
            val phone = binding.edtPhoneNumber.text.toString()
            var text = ""
            val pattern: Pattern = Pattern.compile(" ")  // text dagi bo'sh joylarni olib tashlaydi
            val words: Array<String> = pattern.split(phone)
            for (word in words) {
                text+=word
            }
            if (text!="" && text.length==9){
                text="+998"+text
                val bundle=Bundle()
                bundle.putString("phoneNumber",text)
                findNavController().navigate(R.id.OTPAFragment,bundle)

            }
            else{
                binding.edtPhoneNumber.requestFocus()

                binding.thisphone.setTextColor(Color.parseColor("#FFF20606"))
                val thread=Thread{
                    Thread.sleep(500)
                    binding.thisphone.setTextColor(Color.parseColor("#FFFFFF"))

                    val imm = requireContext().getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager  //fokusni edt text ga qaratadi
                    imm.showSoftInput(binding.edtPhoneNumber, InputMethodManager.SHOW_IMPLICIT)
                }
                thread.start()
            }

        }
        return binding.root
    }


}