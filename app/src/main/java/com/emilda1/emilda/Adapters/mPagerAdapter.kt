package com.emilda1.emilda.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.emilda1.emilda.Fragments.onBoardingScreens.Screen1
import com.emilda1.emilda.Fragments.onBoardingScreens.Screen3
import com.emilda1.emilda.Fragments.onBoardingScreens.screen2

class mPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> Screen1()
            1 -> screen2()
            else -> Screen3()
        }
    }

    override fun getCount(): Int {
        return 3
    }

}