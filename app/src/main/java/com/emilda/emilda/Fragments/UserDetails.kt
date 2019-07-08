package com.emilda.emilda.Fragments


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.emilda.emilda.MainActivities.DetailsActivity
import com.emilda.emilda.R
import com.emilda.emilda.Utils.UserType
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_screen3.*
import kotlinx.android.synthetic.main.fragment_user_details.*

class UserDetails : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submit_user_details.setOnClickListener {
            submit_user_details.visibility = View.GONE
            loading_dots.visibility = View.VISIBLE
            val firstName = firstname_login.text.toString()
            val lastName = lastName_login.text.toString()
            val occup = occupation_login.text.toString()
            val email = email_login.text.toString()

            val User = UserType(firstName, lastName, email, occup)

            val FirebaseUser = FirebaseAuth.getInstance().currentUser

            val ProfileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(firstName)
                .build()

            FirebaseUser?.updateProfile(ProfileUpdates)?.addOnCompleteListener {
                if (it.isSuccessful) {
                    FirebaseDatabase.getInstance().reference.child("UsersData").child(FirebaseUser.uid).setValue(User)
                        .addOnCompleteListener {
                            val intent = Intent(context, DetailsActivity::class.java)
                            startActivity(intent)
                            activity?.finish()
                        }
                }
            }


        }


    }


}
