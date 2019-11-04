package com.emilda.emildaapp.Fragments


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.emilda.emildaapp.MainActivities.DetailsActivity

import com.emilda.emildaapp.R
import com.emilda.emildaapp.Viewmodels.DesktopSupportViewModel
import com.emilda.emildaapp.Viewmodels.PrintingViewModel
import kotlinx.android.synthetic.main.delivery_location.*
import kotlinx.android.synthetic.main.requests_items.*
import java.text.SimpleDateFormat
import java.util.*

class DesktopSupport2 : Fragment() {
    lateinit var model: DesktopSupportViewModel
    private var DeliveryLocation: String = ""
    private var Timing: String = ""
    private var Date: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_desktop_support2, container, false)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model = activity?.run {
            ViewModelProviders.of(this).get(DesktopSupportViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        val Calendar = Calendar.getInstance()
        val Df = SimpleDateFormat("dd/MM/yyyy")
        val TodaysDate = Df.format(Calendar.time)
        Date = TodaysDate



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        OnRadioButtonClicked()
        CalendarSupport()
        submitSupport.setOnClickListener {
            UpdateViewModel()
            model.UploadData()
            Toast.makeText(context,"Request Added Successfully",Toast.LENGTH_LONG).show()
            val intent = Intent(activity,DetailsActivity::class.java)
            startActivity(intent)
        }
    }


    private fun OnRadioButtonClicked() {
        location.clearCheck()
        location.setOnCheckedChangeListener { radioGroup, i ->
            val rb = radioGroup.findViewById(i) as RadioButton
            DeliveryLocation = rb.text.toString()
        }

        timing_radio.clearCheck()
        timing_radio.setOnCheckedChangeListener { radioGroup, i ->
            val rb = radioGroup.findViewById(i) as RadioButton
            Timing = rb.text.toString()
        }
    }


    private fun CalendarSupport() {
        calendar_support.setOnDateChangeListener { calendarView, year, month, day ->
            val mDate = "$day/$month/$year"
            Date = mDate
        }
    }

    private fun UpdateViewModel(){
        model.Date = Date
        model.Time = Timing
        model.DeliveryLocation = DeliveryLocation
    }





}
