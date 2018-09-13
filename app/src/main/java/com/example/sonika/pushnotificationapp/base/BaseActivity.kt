package com.example.sonika.pushnotificationapp.base

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.sonika.pushnotificationapp.R
import com.example.sonika.pushnotificationapp.adapter.TabAdapter
import com.example.sonika.pushnotificationapp.utils.getToastMessage
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.toolbar_main.*

abstract class BaseActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    abstract val layoutId: Int
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        setUpToolBar()
        setUpDrawer()
        setUpViewPager()


    }

    private fun openFragment(fragment: Fragment) {
        val fm = this.supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.container, fragment)
        ft.commit()

    }

    private fun setUpDrawer() {
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar_home, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)

    }

    private fun setUpViewPager() {
        val adapter = TabAdapter(supportFragmentManager)
        viewPager.adapter = adapter
        tab_layout.setupWithViewPager(viewPager)
    }

    private fun setUpToolBar() {
        setSupportActionBar(toolbar_home)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_camera -> getToastMessage("Camera clicked")
            R.id.nav_slideshow -> getToastMessage("SLideshow clicked")
            R.id.nav_gallery -> getToastMessage("gallery clicked")
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}