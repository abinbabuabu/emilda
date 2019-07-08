package com.emilda.emilda.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.navigation.fragment.findNavController
import com.emilda.emilda.Adapters.SupportViewPagerAdapter

import com.emilda.emilda.R
import kotlinx.android.synthetic.main.activity_desktop_support.*
import kotlinx.android.synthetic.main.fragment_desktop_support1.*

class DesktopSupport1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_desktop_support1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        next_btn_support1.setOnClickListener {
            var flag = 0
            if (support_device_type.text.isEmpty()) {
                support_device_type.error = "This field is required"
                flag = 1
            }
            if (brand_name.text!!.isEmpty()) {
                brand_name.error = "This field is required"
                flag = 1
            }
            if (model_name.text!!.isEmpty()) {
                model_name.error = "This field is required"
                flag = 1
            }
            if (problem_desc.text!!.isEmpty()) {
                problem_desc.error = "This field is required"
                flag = 1
            }

            if (flag == 0) {
                findNavController().navigate(R.id.desktopSupport2)
            }
        }


    }


}