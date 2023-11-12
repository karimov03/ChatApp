package com.example.chatapp.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.chatapp.databinding.FragmentOTPABinding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit


class OTPAFragment : Fragment() {
    private var auth: FirebaseAuth? = null
    private var storedVerificationId: String = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    private val binding by lazy { FragmentOTPABinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val phoneNumber = arguments?.getString("phoneNumber")
        binding.thisphone.text = phoneNumber

        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                storedVerificationId = verificationId
                resendToken = token
                Toast.makeText(context, "Sent", Toast.LENGTH_SHORT).show()
            }

            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                Toast.makeText(context, "${p0.smsCode.toString()}", Toast.LENGTH_SHORT).show()
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Toast.makeText(context, "${p0.message}", Toast.LENGTH_SHORT).show()
            }
        }

        auth = FirebaseAuth.getInstance()

        val options = phoneNumber?.let {
            PhoneAuthOptions.newBuilder(auth!!)
                .setPhoneNumber(it)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(requireActivity())
                .setCallbacks(callbacks)
                .build()
        }

        PhoneAuthProvider.verifyPhoneNumber(options!!)

        binding.btnGetStarted.setOnClickListener {
            val code = binding.edtPhoneNumber.text.toString().trim()
            if (code.isNotEmpty()) {
                val credential = PhoneAuthProvider.getCredential(storedVerificationId, code)
                signInWithPhoneAuthCredential(credential)
            } else {
                Toast.makeText(context, "Kiritilgan kod xato", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth!!.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Complate Jura", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(context, "Tasdiqlashda xatolik: ${task.exception}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
