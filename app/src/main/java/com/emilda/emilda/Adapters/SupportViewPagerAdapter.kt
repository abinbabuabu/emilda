package com.emilda.emilda.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.emilda.emilda.Fragments.DesktopSupport1
import com.emilda.emilda.Fragments.onBoardingScreens.Screen1
import com.emilda.emilda.Fragments.onBoardingScreens.Screen3
import com.emilda.emilda.Fragments.onBoardingScreens.screen2

class SupportViewPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> DesktopSupport1()
            else ->DesktopSupport1()
        }
    }

    override fun getCount(): Int {
        return 2
    }
}