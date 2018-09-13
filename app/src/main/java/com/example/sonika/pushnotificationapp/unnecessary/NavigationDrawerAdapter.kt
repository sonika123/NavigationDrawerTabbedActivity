package com.example.sonika.pushnotificationapp.unnecessary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sonika.pushnotificationapp.R
import kotlinx.android.synthetic.main.navigation_row_item.view.*

class NavigationDrawerAdapter(private val navItems: ArrayList<MainMenu>,
                              private val itemSelectListener: (Int) -> Unit) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.navigation_row_item, parent, false)
        return DataViewHolder(v)
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: MainMenu, itemSelectListener: (Int) -> Unit, position: Int) {
            with(itemView) {

                navigationTextView.text = item.menuTitle
                navigationImageView.setImageResource(item.menuIcon)
                setOnClickListener { itemSelectListener(position) }
            }
        }
    }

    override fun getItemCount(): Int {
        return navItems.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DataViewHolder).bind(navItems[position], itemSelectListener, position)
    }
}

