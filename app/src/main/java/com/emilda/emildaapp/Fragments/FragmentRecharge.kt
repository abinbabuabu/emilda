package com.emilda.emildaapp.Fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.emilda.emildaapp.R
import com.emilda.emildaapp.Viewmodels.DetailsViewModel
import com.google.firebase.auth.FirebaseAuth
import com.razorpay.Checkout
import getCoinsList
import getRupeesList
import kotlinx.android.synthetic.main.fragment_recharge.*
import org.json.JSONObject

class FragmentRecharge : Fragment() {
    lateinit var Email:String
    lateinit var Phone:String
    lateinit var model:DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = activity?.run {
            ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recharge, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val User = FirebaseAuth.getInstance().currentUser
        Phone = User?.phoneNumber ?: ""

        Checkout.preload(activity?.applicationContext)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRupeesNcoins()
        setPaymentButtons()
        model.getEmail().observe(this, Observer {
            Email = it
        })

        model.AvailableCredits.observe(this, Observer {
            available_credit.text = it.toString()
        })
    }

    private fun startPayment(paisa: String) {

        val co = Checkout()

        try {
            val options = JSONObject()
            options.put("name", "Emilda Solutions")
            options.put("description", "Credit Charges")
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
            options.put("currency", "INR")
            options.put("amount", paisa)

            val preFill = JSONObject()

            preFill.put("email", Email)
            preFill.put("contact", Phone)

            options.put("prefill", preFill)

            co.open(this.activity, options) //current activity is passed
        } catch (e: Exception) {
            Log.d("xy","Error")
            e.printStackTrace()
        }

    }

    private fun getAsPaisa(rupees: Int): String {
        val paisa = rupees * 100
        return paisa.toString()
    }

    private fun setRupeesNcoins() {
        val rupeesList = getRupeesList()
        val coinsList = getCoinsList()
        rupees_1.text = rupeesList[0].toString()
        rupees_2.text = rupeesList[1].toString()
        rupees_3.text = rupeesList[2].toString()
        rupees_4.text = rupeesList[3].toString()
        rupees_5.text = rupeesList[4].toString()

        coin_1.text = coinsList[0].toString()
        coin_2.text = coinsList[1].toString()
        coin_3.text = coinsList[2].toString()
        coin_4.text = coinsList[3].toString()
        coin_5.text = coinsList[4].toString()
    }

    private fun setPaymentButtons(){
        val rupeesList = getRupeesList()
        pay_1.setOnClickListener {
            model.amountSelected = rupeesList[0]
            startPayment(getAsPaisa(rupeesList[0]))
        }
        pay_2.setOnClickListener {
            model.amountSelected = rupeesList[1]
            startPayment(getAsPaisa(rupeesList[1]))
        }
        pay_3.setOnClickListener {
            model.amountSelected = rupeesList[2]
            startPayment(getAsPaisa(rupeesList[2]))
        }
        pay_4.setOnClickListener {
            model.amountSelected = rupeesList[3]
            startPayment(getAsPaisa(rupeesList[3]))
        }
        pay_5.setOnClickListener {
            model.amountSelected = rupeesList[4]
            startPayment(getAsPaisa(rupeesList[4]))
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("xy","Resume Called")
        pay_1.text = "Add"
        pay_2.text = "Add"
        pay_3.text = "Add"
        pay_4.text = "Add"
        pay_5.text = "Add"
    }


}
