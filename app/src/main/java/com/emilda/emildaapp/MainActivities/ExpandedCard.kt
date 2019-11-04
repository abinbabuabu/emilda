package com.emilda.emildaapp.MainActivities

import ResolvePosition
import ResolveWorksList
import android.os.Build
import android.os.Bundle
import android.transition.Slide
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.emilda.emildaapp.Adapters.HowsAdapter
import com.emilda.emildaapp.Adapters.ListWorksAdapter
import com.emilda.emildaapp.Adapters.PortfolioAdapter
import com.emilda.emildaapp.Dataclass.ProjectSupport
import com.emilda.emildaapp.R
import com.emilda.emildaapp.Viewmodels.ExpandedCardViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import getSmallIcons
import getText1
import getText2
import kotlinx.android.synthetic.main.activity_expanded_card.*
import kotlinx.android.synthetic.main.description_text_field.view.*
import kotlinx.android.synthetic.main.extended_card_template.*

class ExpandedCard : AppCompatActivity() {
    lateinit var ActivityTag: String
    lateinit var mViewModel: ExpandedCardViewModel
    lateinit var adapter: PortfolioAdapter
    lateinit var dialogBuilder: AlertDialog.Builder
    lateinit var FirebaseDatabase: DatabaseReference
    private var phoneNumber: String = ""
    private var UserID:String =""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expanded_card)
        val CardPosition = intent?.extras?.get("position") as Int

        ActivityTag = ResolvePosition(CardPosition)
        text1.text = ActivityTag


        val toolbar: Toolbar = this.findViewById(R.id.printing_toolbar)

        // toolbar.title = ActivityTag
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        FirebaseDatabase = com.google.firebase.database.FirebaseDatabase.getInstance()
            .reference.child("Project Requests")

        val Auth = FirebaseAuth.getInstance()
        UserID =Auth.uid ?: "NUll"
        phoneNumber = Auth.currentUser?.phoneNumber ?: "Null"



        mViewModel = ViewModelProviders.of(this).get(ExpandedCardViewModel::class.java)
        val options = mViewModel.getPortfolio(CardPosition)
        portfolio_rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        adapter = PortfolioAdapter(options)
        portfolio_rv.adapter = adapter

        type_recycler.layoutManager = LinearLayoutManager(this)
        type_recycler.adapter = ListWorksAdapter(ResolveWorksList(CardPosition))

        how_recycle.layoutManager = LinearLayoutManager(this)
        how_recycle.adapter =
            HowsAdapter(getText1(CardPosition), getText2(CardPosition), getSmallIcons(CardPosition))

        when (CardPosition) {
            0 -> {
                Glide.with(this).load(R.drawable.web_dev).centerCrop().into(expand_image)
            }
            1 -> {
                Glide.with(this).load(R.drawable.app_dev).centerCrop().into(expand_image)
            }
            2 -> {
                Glide.with(this).load(R.drawable.graphic_design).centerCrop().into(expand_image)
            }

            4 -> {
                Glide.with(this).load(R.drawable.digital_market).centerCrop().into(expand_image)
            }

        }




        start_project.setOnClickListener {
            dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("What is your mind ?")

            val view = LayoutInflater.from(this).inflate(R.layout.description_text_field, null)
            dialogBuilder.setView(view)

            dialogBuilder.setPositiveButton("Submit") { _, _ -> }
            dialogBuilder.setNegativeButton("Cancel") { _, _ -> }


            val dialog = dialogBuilder.create()
            dialog.show()

            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                if (view.project_desc.text.isNullOrEmpty()) {
                    view.project_desc.error = "This is Required"
                } else {
                    val desc = view.project_desc.text.toString()
                    UploadData(desc)
                    Toast.makeText(view.context,"Support Request Added",Toast.LENGTH_LONG).show()
                    dialog.cancel()
                }
            }

            // dialog.getButton()

//            dialog.setNegativeButton("Cancel") { dialogInterface, p1 ->
//                //dialog.setCancelable(true)
//            }


//
        }


    }


    private fun UploadData(data: String) {
        val key = FirebaseDatabase.push().key ?: "Error"
        val UploadData = ProjectSupport(UserID,phoneNumber,data)
        FirebaseDatabase.child(key).setValue(UploadData)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        adapter.stopListening()
        super.onStop()
    }

    fun exitEnterAnim() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val explode = Slide()
            explode.duration = 60000
            window.allowEnterTransitionOverlap = true
            window.exitTransition = explode
            Log.d("xyz", "Animation Called")

        }
    }


}
