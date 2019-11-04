package com.emilda.emildaapp.Fragments


import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.emilda.emildaapp.LoginActivities.PhoneLogin
import com.emilda.emildaapp.MainActivities.DetailsActivity
import com.emilda.emildaapp.MainActivities.FeedbackActivity
import com.emilda.emildaapp.MainActivities.SupportActivity
import com.emilda.emildaapp.R
import com.emilda.emildaapp.Viewmodels.DetailsViewModel
import com.emilda.emildaapp.Viewmodels.PrintingViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_profile.*

class FragmentProfile : Fragment() {
    lateinit var model:DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.activity_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val point = Point()
        val size = activity?.windowManager?.defaultDisplay?.getSize(point)
        val location = IntArray(2)
        small_cards.getLocationInWindow(location)
        model = activity?.run {
            ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        } ?: throw Exception("Invalid Activity")


        if (resources.displayMetrics.widthPixels > resources.displayMetrics.heightPixels) {
            yellow_bg.layoutParams.height = point.y / 2 + 100
        } else {
            yellow_bg.layoutParams.height = point.y / 3 - 50
        }

        setClickListeners()
        setProfileDetails()




    }

    private fun setClickListeners(){
        support_row.setOnClickListener {
            val intent = Intent(context, SupportActivity::class.java)
            startActivity(intent)
        }

        feedback_row.setOnClickListener {
            val intent = Intent(context, FeedbackActivity::class.java)
            startActivity(intent)
        }

        logout_row.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(context, PhoneLogin::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }

    private fun setProfileDetails(){
        model.getName().observe(this, Observer {
            profile_name.text = it
        })
        model.getEmail().observe(this, Observer {
            profile_email.text = it
        })
        model.AvailableCredits.observe(this, Observer {
            credit_bal_prof.text = it.toString()
        })
        profile_phone.text = model.CurrentUser?.phoneNumber

    }
}
