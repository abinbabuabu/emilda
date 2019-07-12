package com.emilda1.emilda.Fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.emilda1.emilda.Dataclass.DeliveryDetails
import com.emilda1.emilda.R
import kotlinx.android.synthetic.main.delivery_location.*
import java.text.SimpleDateFormat
import java.util.*

class Printing2 : Fragment() {
    var deliverylocation: String = ""
    var deliverytime: String = ""
    var deliveryDate: String = ""
    var others: String = ""
    var FlagOthers = false



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_printing2, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        radioClick()
        CalenderSetup()

    }

    override fun onStart() {
        super.onStart()
        submit_support2.setOnClickListener {
            var Flag = true
            if (location.checkedRadioButtonId == others_radio.id) {
                if (others_et.text!!.isEmpty()) {
                    others_et.error = "Field is Required"
                    Flag = false
                }
            }

            if (Flag) {
                others = others_et.text.toString()
                val deliveryDetails = DeliveryDetails(deliverytime, deliveryDate, deliverylocation, others)
                findNavController().popBackStack()
                findNavController().navigate(R.id.uploadingStatus)
            }


        }
    }


    fun radioClick() {
        location.check(mba_canteen_radio.id)
        deliverylocation = "MBA Canteen"
        deliverytime = "Morning"

        location.setOnCheckedChangeListener { radioGroup, i ->
            when (radioGroup.findViewById<RadioButton>(i)) {
                mba_canteen_radio -> {
                    deliverylocation = "MBA Canteen"
                }
                open_audi_radio -> {
                    deliverylocation = "Open Audi"
                }
                others_radio -> {
                    FlagOthers = true
                    deliverylocation = "Others"
                    others_et.isEnabled = true
                    others_et.requestFocus()
                }

            }
        }

        timing_radio.check(morning_radio.id)
        timing_radio.setOnCheckedChangeListener { radioGroup, i ->
            when (radioGroup.findViewById<RadioButton>(i)) {
                morning_radio -> {
                    deliverytime = "Morning"
                }
                after_noon_radio -> {
                    deliverytime = "AfterNoon"
                }
                evening_radio -> {
                    deliverytime = "Evening"
                }

            }
        }
    }


    fun CalenderSetup() {
        val current = Calendar.getInstance().timeInMillis

        val date = SimpleDateFormat("dd-MM-yyyy")
        deliveryDate = date.format(current)

        calendar_support.minDate = current

        calendar_support.setOnDateChangeListener { calendarView, year, month, d ->
            val dates = Date(year - 1900, month, d).time
            deliveryDate = date.format(dates)

        }
    }



}
