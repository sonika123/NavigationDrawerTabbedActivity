package com.example.sonika.pushnotificationapp

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.sonika.pushnotificationapp.tabLayout.TabAdapter
import com.google.android.material.navigation.NavigationView
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.toolbar_main.*


//class HomeActivity : AppCompatActivity(), NavigationDrawerCallbacks {
class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setUpToolBar()
        setUpDrawer()
        setUpViewPager()

        val token = FirebaseInstanceId.getInstance().token


//        tab_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        openFragment(HomeFragment())

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
            R.id.nav_camera -> Toast.makeText(this, "Camera clicked", Toast.LENGTH_SHORT).show()
            R.id.nav_slideshow -> Toast.makeText(this, "Slideshow clicked", Toast.LENGTH_SHORT).show()
            R.id.nav_gallery -> Toast.makeText(this, "Gallery clicked", Toast.LENGTH_SHORT).show()
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setUpDrawer() {
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar_home, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)

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
        setSupportActionBar(toolbar_home)
    }

}
//do this for right tab_navigation drawer using menu
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.nav_menu, menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.navigation_drawer -> {
//                if (drawer.isDrawerOpen(Gravity.END)) {
//                    drawer.closeDrawer(Gravity.END)
//                } else {
//                    drawer.openDrawer(Gravity.END)
//                }
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

//    override fun onBackPressed() {
//        if (drawer.isDrawerOpen(Gravity.END)) {
//            drawer.closeDrawer(Gravity.END)
//        } else {
//            super.onBackPressed()
//        }
//    }

//    private fun closeDrawer() {
//        if (drawer.isDrawerOpen(Gravity.END))
//            drawer.closeDrawer(Gravity.END)
//    }
//
//    override fun onNavigationDrawerItemSelected(position: Int?) {
//        closeDrawer()
//        when (position) {
//            0 -> Toast.makeText(this, "Camera Clicked", Toast.LENGTH_SHORT).show()
//            1 -> Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show()
//            2 -> Toast.makeText(this, "Slideshow clicked", Toast.LENGTH_SHORT).show()
//        }
//
//    }

//}


