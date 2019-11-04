package com.emilda.emildaapp.Fragments.onBoardingScreens


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.emilda.emildaapp.LoginActivities.PhoneLogin
import com.emilda.emildaapp.R

import kotlinx.android.synthetic.main.fragment_screen3.*


class Screen3 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_screen3, container, false)
    }


}
