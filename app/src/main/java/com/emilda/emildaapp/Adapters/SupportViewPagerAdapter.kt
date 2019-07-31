package com.emilda.emildaapp.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.emilda.emildaapp.Fragments.DesktopSupport1

class SupportViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> DesktopSupport1()
            else -> DesktopSupport1()
        }
    }

    override fun getCount(): Int {
        return 2
    }
}