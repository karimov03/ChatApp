package com.example.chatapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentStartBinding

class StartFragment : Fragment() {
    private val binding by lazy { FragmentStartBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.btnGetStarted.setOnClickListener {
            findNavController().navigate(R.id.phoneNumberFragment)
        }
        return  binding.root
    }


}