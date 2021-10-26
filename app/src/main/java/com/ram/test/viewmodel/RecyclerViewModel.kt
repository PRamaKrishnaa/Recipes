package com.ram.test.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ram.test.model.RecyclerViewData
import com.ram.test.network.RetroInstance
import com.ram.test.network.RetroService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerViewModel : ViewModel() {
    lateinit var recyclerViewList: MutableLiveData<List<RecyclerViewData>>

    init {
        recyclerViewList = MutableLiveData()

    }

    fun getRecyclerListObserver(): MutableLiveData<List<RecyclerViewData>> {
        return recyclerViewList
    }

    //Get data from API
    fun makeApiCall() {
        val retroGetData = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroGetData.getDataFromApi()
        call.enqueue(object : Callback<List<RecyclerViewData>> {
            override fun onResponse(
                call: Call<List<RecyclerViewData>>,
                response: Response<List<RecyclerViewData>>
            ) {

             //   Log.d("SucResponse", response.body().toString())
                if (response.isSuccessful) {
                    // recyclerViewAdapter.setListData(response.body()?.items!!)
                    //  recyclerViewAdapter.notifyDataSetChanged()

                    recyclerViewList.postValue(response.body())
                } else {
                    recyclerViewList.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<RecyclerViewData>>, t: Throwable) {
             //   Log.d("FalResponse", t.message.toString())
                recyclerViewList.postValue(null)
                //  Toast.makeText(this@MainActivity, "Error in getting the data", Toast.LENGTH_LONG).show()
            }
        })
    }
}