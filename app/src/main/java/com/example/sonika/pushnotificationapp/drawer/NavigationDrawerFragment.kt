package com.example.sonika.pushnotificationapp.drawer

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sonika.pushnotificationapp.R
import kotlinx.android.synthetic.main.fragment_navigation_drawer.*
import java.util.*

class NavigationDrawerFragment : Fragment() {

    private var mCallbacks: NavigationDrawerCallbacks? = null
    private lateinit var mContext: Context
    private var adapter: NavigationDrawerAdapter? = null
    private val menus: ArrayList<MainMenu>
        get() {
            val items = ArrayList<MainMenu>()

            items.add(MainMenu("Camera  ", R.drawable.ic_navigation_icon))
            items.add(MainMenu("Home", R.drawable.ic_navigation_icon))
            items.add(MainMenu("SLideshow", R.drawable.ic_navigation_icon))


            return items
        }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context!!
        try {
            mCallbacks = mContext as NavigationDrawerCallbacks?
        } catch (e: ClassCastException) {
            e.printStackTrace()
            //throw ClassCastException("Activity must implement NavigationDrawerCallbacks.")
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpNavigationList()
    }


    private fun setUpNavigationList() {

        recyclerview.setLayoutManager(LinearLayoutManager(context))
        recyclerview.setHasFixedSize(true)
        recyclerview.isNestedScrollingEnabled = false

        adapter = NavigationDrawerAdapter(menus)
        {
            mCallbacks!!.onNavigationDrawerItemSelected(it)
        }

        recyclerview.adapter = adapter

    }
}
