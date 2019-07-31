package com.emilda.emildaapp.Fragments


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.emilda.emildaapp.Dataclass.UserType
import com.emilda.emildaapp.MainActivities.DetailsActivity
import com.emilda.emildaapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
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

            val User = UserType(firstName, lastName, email, occup,10,"$firstName $lastName")

            val firebaseUser = FirebaseAuth.getInstance().currentUser

            val ProfileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(firstName)
                .build()


            firebaseUser?.updateProfile(ProfileUpdates)?.addOnCompleteListener {
                if (it.isSuccessful) {
                    FirebaseDatabase.getInstance().reference.child("UsersData").child(firebaseUser.uid).setValue(User)
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
