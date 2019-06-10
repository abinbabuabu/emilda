package com.emilda.emilda

import ResolvePosition
import android.os.Build
import android.os.Bundle
import android.transition.Explode
import android.transition.Slide
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import java.time.LocalDate

class ExpandedCard : AppCompatActivity() {
    lateinit var ActivityTag: String
    lateinit var mViewModel: ExpandedCardViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expanded_card)
        val CardPosition = intent?.extras?.get("position") as Int
        ActivityTag = ResolvePosition(CardPosition)
        val toolbar: Toolbar = this.findViewById(R.id.printing_toolbar)
        toolbar.title = ActivityTag
        setSupportActionBar(toolbar)
        mViewModel = ViewModelProviders.of(this).get(ExpandedCardViewModel::class.java)
        mViewModel?.getPortfolio(CardPosition).observe(this, Observer {
            Log.d("xyz", it[0])
        })

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

    }
    fun exitEnterAnim(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val explode = Slide()
            explode.duration = 60000
            window.allowEnterTransitionOverlap =true
            window.exitTransition = explode
            Log.d("xyz","Animation Called")

        }
    }
}
