package com.emilda.emilda.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.emilda.emilda.R
import kotlinx.android.synthetic.main.fragment_printing1.*

class Printing1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_printing1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        next_print1.setOnClickListener {
            var flag = true
            if(color_mode.text.isEmpty()) {
                flag = false
                color_mode.error = "This field is Required"
            }
            if(paper_size.text.isEmpty()) {
                flag = false
                paper_size.error = "This field is Required"
            }
            if(paper_type.text.isEmpty()) {
                flag = false
                paper_type.error = "This field is Required"
            }
            if(duplex.text.isEmpty()) {
                flag = false
                duplex.error = "This field is Required"
            }
            if(document_binding.text.isEmpty()) {
                flag = false
                document_binding.error = "This field is Required"
            }

            if(flag)
            findNavController().navigate(R.id.printing2)
        }
    }


}
