package com.example.sonika.pushnotificationapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.sonika.pushnotificationapp.views.home.HomeFragment
import com.example.sonika.pushnotificationapp.views.tab2.Tab2Fragment
import com.example.sonika.pushnotificationapp.views.tab3.Tab3Fragment


class TabAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            HomeFragment()
        } else if (position == 1) {
            Tab2Fragment()
        } else {
            Tab3Fragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Home"
            1 -> return "Tab 2"
            2 -> return "Tab 3"
            else -> return "title"
        }
    }
}