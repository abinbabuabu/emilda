package com.emilda1.emilda.Fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.emilda1.emilda.R
import kotlinx.android.synthetic.main.activity_phone_login.*


class PhoneFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_phone_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        next_btn_login.setOnClickListener {
            val phone = phone_number.text.toString()
            Log.d("phone", phone)
            if (phone.length != 10) {
                Toast.makeText(context, "Invalid Mobile Number", Toast.LENGTH_LONG).show()
            } else {
                val directions = PhoneFragmentDirections.actionPhoneFragmentToOtpFragment(phone)
                findNavController().navigate(directions)
            }
        }


    }


}
