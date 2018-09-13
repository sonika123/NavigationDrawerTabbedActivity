package com.example.sonika.pushnotificationapp.views.home


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sonika.pushnotificationapp.R
import com.example.sonika.pushnotificationapp.adapter.MyAdapter
import com.example.sonika.pushnotificationapp.base.BaseFragment
import com.example.sonika.pushnotificationapp.model.ExchangeRate
import com.example.sonika.pushnotificationapp.utils.ApiInterface
import com.example.sonika.pushnotificationapp.utils.getToastMessage
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : BaseFragment() {
    override val layoutId: Int get() = R.layout.fragment_home
    val exchangeRateList: ArrayList<ExchangeRate> = arrayListOf()
    lateinit var mAdapter: MyAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler_view_rate_list.layoutManager = LinearLayoutManager(context)
        mAdapter = MyAdapter(exchangeRateList);
        recycler_view_rate_list.adapter = mAdapter
        retrofitOperations()
    }

    private fun retrofitOperations() {
        if (isOnline()) {
            client = ApiInterface.retrofit.create(ApiInterface::class.java)

// Fetch a list of the repositories.
            val call = client.getAllExchangeRate()

// Execute the call asynchronously. Get a positive or negative callback.
            call.enqueue(object : Callback<List<ExchangeRate>> {

                override fun onFailure(call: Call<List<ExchangeRate>>?, t: Throwable?) {
                    activity!!.getToastMessage("failed")
                }

                override fun onResponse(call: Call<List<ExchangeRate>>, response: Response<List<ExchangeRate>>) {
                   activity!!.getToastMessage("success")
                    val responseString = response.body()

                    generateList(response.body())
                    Log.e("hi", responseString.toString())


                }
            })

        } else {
            activity!!.getToastMessage("No Internet")
        }
    }

    private fun generateList(exchangeRateList: List<ExchangeRate>?) {
        mAdapter.exchageRateList = exchangeRateList!!
        mAdapter.notifyDataSetChanged()
    }
}