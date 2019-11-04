package com.emilda.emildaapp.MainActivities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import com.emilda.emildaapp.R
import com.emilda.emildaapp.Viewmodels.DesktopSupportViewModel


class SupportActivity : AppCompatActivity() {
    lateinit var viewModel:DesktopSupportViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_support)
        val toolbar: Toolbar = findViewById(R.id.support_toolbar)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProviders.of(this).get(DesktopSupportViewModel::class.java)
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

}
