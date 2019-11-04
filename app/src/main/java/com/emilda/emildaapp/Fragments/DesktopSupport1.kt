package com.emilda.emildaapp.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.emilda.emildaapp.R
import com.emilda.emildaapp.Viewmodels.DesktopSupportViewModel
import com.emilda.emildaapp.Viewmodels.PrintingViewModel
import kotlinx.android.synthetic.main.fragment_desktop_support1.*
import kotlinx.android.synthetic.main.fragment_printing1.*

class DesktopSupport1 : Fragment() {
    var FixType = ""
    lateinit var model: DesktopSupportViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_desktop_support1, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = activity?.run {
            ViewModelProviders.of(this).get(DesktopSupportViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val FixTypes = arrayOf("Hardware", "Software")
        val FixTypeAdapter = ArrayAdapter(context!!, R.layout.drop_down_menu_item, FixTypes)

//Color Mode
        support_device_type.setAdapter(FixTypeAdapter)
        support_device_type.setOnItemClickListener { adapterView, view, position, l ->
            FixType = adapterView.getItemAtPosition(position).toString()
            support_device_type.hint = FixType
            support_device_type.error = null
        }

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
                AddDataToViewModel()
                findNavController().navigate(R.id.desktopSupport2)
            }
        }


    }

 private fun AddDataToViewModel(){
     model.BrandName = brand_name.text.toString()
     model.ModelName = model_name.text.toString()
     model.FixType = FixType
     model.ProblemDesc = problem_desc.text.toString()
 }

}
