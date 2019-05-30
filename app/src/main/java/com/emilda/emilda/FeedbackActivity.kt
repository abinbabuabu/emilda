package com.emilda.emilda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar


class FeedbackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // val toolbar:Toolbar  = findViewById(R.id.feedback_toolbar)
        //setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        setContentView(R.layout.activity_feedback)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.home ->{
                onBackPressed()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
