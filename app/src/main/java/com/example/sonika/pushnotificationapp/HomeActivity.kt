package com.example.sonika.pushnotificationapp

import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.sonika.pushnotificationapp.drawer.NavigationDrawerCallbacks
import com.example.sonika.pushnotificationapp.tabLayout.TabAdapter
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.toolbar_main.*


class HomeActivity : AppCompatActivity(), NavigationDrawerCallbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setUpViewPager()

        val token = FirebaseInstanceId.getInstance().token

//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        openFragment(HomeFragment())




        setUpToolBar()

    }

    private fun openFragment(fragment: Fragment) {
        val fm = this.supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.container, fragment)
        ft.commit()

    }

    private fun setUpViewPager() {

        val adapter = TabAdapter(supportFragmentManager)
        viewPager.adapter = adapter
        tab_layout.setupWithViewPager(viewPager)

    }

    private fun setUpToolBar() {
//        toolbar_home.setNavigationIcon(R.drawable.ic_navigation_icon)
//
        setSupportActionBar(toolbar_home)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.navigation_drawer -> {
                if (drawer.isDrawerOpen(Gravity.END)) {
                    drawer.closeDrawer(Gravity.END)
                } else {
                    drawer.openDrawer(Gravity.END)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(Gravity.END)) {
            drawer.closeDrawer(Gravity.END)
        } else {
            super.onBackPressed()
        }
    }

    private fun closeDrawer() {
        if (drawer.isDrawerOpen(Gravity.END))
            drawer.closeDrawer(Gravity.END)
    }

    override fun onNavigationDrawerItemSelected(position: Int?) {
        closeDrawer()
        when (position) {
            0 -> Toast.makeText(this, "Camera Clicked", Toast.LENGTH_SHORT).show()
            1 -> Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show()
            2 -> Toast.makeText(this, "Slideshow clicked", Toast.LENGTH_SHORT).show()
        }

    }

}

