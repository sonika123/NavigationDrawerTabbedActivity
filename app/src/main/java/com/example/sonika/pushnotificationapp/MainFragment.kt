package com.example.sonika.pushnotificationapp


import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sonika.pushnotificationapp.ApiInterface.Companion.retrofit
import kotlinx.android.synthetic.main.fragment_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainFragment : Fragment() {

    val exchangeRateList: ArrayList<ExchangeRate> = arrayListOf()
    lateinit var mAdapter: MyAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        return view;

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recycler_view_rate_list.layoutManager = LinearLayoutManager(context)
        mAdapter = MyAdapter(exchangeRateList);
        recycler_view_rate_list.adapter = mAdapter


        retrofitOperations()
    }

    private fun retrofitOperations() {
        if (isOnline()) {
            val client = retrofit.create(ApiInterface::class.java)

// Fetch a list of the repositories.
            val call = client.getAllExchangeRate()


// Execute the call asynchronously. Get a positive or negative callback.
            call.enqueue(object : Callback<List<ExchangeRate>> {

                override fun onFailure(call: Call<List<ExchangeRate>>?, t: Throwable?) {
                    Log.e("helo", "fail")
                    Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<List<ExchangeRate>>, response: Response<List<ExchangeRate>>) {
                    Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()
                    val responseString = response.body()

                    generateList(response.body())
                    Log.e("hi", responseString.toString())


                }

            })

        } else {
            Toast.makeText(context, "no internet", Toast.LENGTH_SHORT).show()

        }
    }

    private fun generateList(exchangeRateList: List<ExchangeRate>?) {
        mAdapter.exchageRateList = exchangeRateList!!

        mAdapter.notifyDataSetChanged()
//                    val list= responseString!!.map { it.Text }
//                    spinner_occupation.adapter =  ArrayAdapter<String>(this@MainActivity,
//                            android.R.layout.simple_spinzner_dropdown_item, list)

    }


    fun isOnline(): Boolean {
        val cm = activity!!.getSystemService(android.content.Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }

}