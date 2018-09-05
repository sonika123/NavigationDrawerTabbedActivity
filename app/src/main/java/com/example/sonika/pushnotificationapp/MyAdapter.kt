package com.example.sonika.pushnotificationapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_adapter_1.view.*

class MyAdapter(var exchageRateList: List<ExchangeRate>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val LAYOUT_TYPE_ONE = 1
    private val LAYOUT_TYPE_TWO = 2


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        when(holder.itemViewType)
//        {
//            LAYOUT_TYPE_ONE -> initLayoutOne(holder as MyViewHolder, position)
//            LAYOUT_TYPE_TWO-> initLayoutTwo(holder as MyViewHolder2, position)
//        }
        if (holder.itemViewType.equals(LAYOUT_TYPE_ONE)) {
            return (holder as MyViewHolder).bind(exchageRateList[position])
        } else if (holder.itemViewType.equals(LAYOUT_TYPE_TWO)) {
            return (holder as MyViewHolder2).bind()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == LAYOUT_TYPE_ONE) {

            val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item_adapter_1, parent, false)
            return MyViewHolder(v)
        } else if (viewType == LAYOUT_TYPE_TWO) {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item_adapter_2, parent, false)
            return MyViewHolder2(v)
        } else {
            throw RuntimeException("The type has to be ONE or TWO")

        }
    }


    override fun getItemCount(): Int = exchageRateList.size

    override fun getItemViewType(position: Int): Int {
        val pos = position + 1
        if (pos % 5 == 0) {
            return LAYOUT_TYPE_TWO
        } else {
            return LAYOUT_TYPE_ONE
        }
    }


    class MyViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {


            //itemView.grey_line
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(exchangeRate: ExchangeRate) {

            itemView.currencyNameTextView.text = exchangeRate.currencyName
            itemView.currencyCodeTextView.text = exchangeRate.currencyCode

            Picasso.get().load("https://demo.eremit.com.my/images/flags/" + exchangeRate.flagCode + ".png").into(itemView.countryFlagImageView)


        }

    }
}