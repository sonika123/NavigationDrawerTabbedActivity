package com.example.sonika.pushnotificationapp.tabLayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.sonika.pushnotificationapp.HomeFragment


class TabAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            HomeFragment()
        } else if (position == 1) {
            Tab2()
        } else {
            Tab3()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Tab 1"
            1 -> return "Tab 2"
            2 -> return "Tab 3"
        }
        return "title"
    }
}