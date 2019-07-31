package com.emilda.emildaapp.Fragments


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.emilda.emildaapp.MainActivities.DetailsActivity
import com.emilda.emildaapp.R
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_otp_screen.*
import kotlinx.android.synthetic.main.login_nav.*
import java.util.concurrent.TimeUnit

class OtpFragment : Fragment() {
    lateinit var mAuth: FirebaseAuth
    var number: String? = null
    var storedVerificationId: String? = null
    var resendToken: PhoneAuthProvider.ForceResendingToken? = null
    var FlagAuto: MutableLiveData<Boolean> = MutableLiveData()

    private var callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            FlagAuto.postValue(true)
            Log.d("Az", "onVerificationCompleted:$credential")
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            Log.d("Az", "onVerificationFailed", e)

            if (e is FirebaseAuthInvalidCredentialsException) {
                Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                // ...
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
                Log.d("Az", "onVerification", e)
            }

            // Show a message and update the UI
        }

        override fun onCodeSent(
            verificationId: String?,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            Log.d("Az", "onCodeSent:" + verificationId!!)
            // Save verification ID and resending token so we can use them later
            storedVerificationId = verificationId
            resendToken = token

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_otp_screen, container, false)
    }

    override fun onStart() {
        super.onStart()
        number = "+91$number"
        if (number != null)
            Log.d("Number", number)
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            number!!,      // Phone number to verify
            60,               // Timeout duration
            TimeUnit.SECONDS, // Unit of timeout
            this.requireActivity(),             // Activity (for callback binding)
            callbacks
        )  // OnVerificationStateChangedCallbacks


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FlagAuto.postValue(false)
        FlagAuto.observe(this, Observer {
            if (it) {
                next_btn_otp.visibility = View.GONE
                loading_otp.visibility = View.VISIBLE
            }
        })

        mAuth = FirebaseAuth.getInstance()
        number = OtpFragmentArgs.fromBundle(arguments!!).phone
        Log.d("Number", number)
        val phoneWithCC = "+91-$number"

        phone_number_otp.text = phoneWithCC

        next_btn_otp.setOnClickListener {
            val code = otp.text.toString()

            if (code.length == 6) {
                next_btn_otp.visibility = View.INVISIBLE
                loading_otp.visibility = View.VISIBLE

                val credential = PhoneAuthProvider.getCredential(storedVerificationId!!, code)
                signInWithPhoneAuthCredential(credential)
            } else {
                Toast.makeText(context, "Invalid OTP", Toast.LENGTH_SHORT).show()
            }


        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //Replace With SnackBar
                    val Newuser = task.result?.additionalUserInfo?.isNewUser

                    if (Newuser !== null) {
                        if (!Newuser) {
                            startActivity(Intent(context, DetailsActivity::class.java))
                            activity?.finish()
                        } else {
                            Log.d("User", "Its a new user Bingo")
                            login_main_nav.findNavController().navigate(R.id.action_otpFragment_to_userDetails)
                        }
                    }


                    // ...
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.d("Az", "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                }
            }
    }


}
