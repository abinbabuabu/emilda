package com.emilda.emilda.Fragments


import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.emilda.emilda.LoginActivities.PhoneLogin
import com.emilda.emilda.MainActivities.FeedbackActivity
import com.emilda.emilda.MainActivities.SupportActivity
import com.emilda.emilda.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_profile.*

class FragmentProfile : Fragment() {

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




        if (resources.displayMetrics.widthPixels > resources.displayMetrics.heightPixels) {
            yellow_bg.layoutParams.height = point.y / 2 + 100
        } else {
            yellow_bg.layoutParams.height = point.y / 3 - 50
        }

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
            val phone = FirebaseAuth.getInstance().currentUser?.phoneNumber
            val intent = Intent(context,PhoneLogin::class.java)
            startActivity(intent)
            activity?.finish()
        }


    }
}
