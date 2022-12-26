package com.example.retrofit

import android.os.Handler.Callback
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.Call
import okhttp3.Response

class MainActivityViewModel : ViewModel() {

    lateinit var recyclerListData: MutableLiveData<UserList>

    init {
        recyclerListData = MutableLiveData()
    }

    fun getUserListObserverable(): MutableLiveData<UserList> {
        return recyclerListData
    }

    fun getUsersList() {
            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
            val call = retroInstance.getUsersList()
        call.enqueue(object : retrofit2.Callback<UserList> {
            override fun onFailure(call: retrofit2.Call<UserList>, t: Throwable) {
                recyclerListData.postValue(null)
            }

            override fun onResponse(
                call: retrofit2.Call<UserList>,
                response: retrofit2.Response<UserList>
            ) {
                if(response.isSuccessful){
                    recyclerListData.postValue(response.body())
                }
                else{
                    recyclerListData.postValue(null)
                }
            }


        })
        }


    fun searchUser(searchText : String) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.searchUsers(searchText)
        call.enqueue(object : retrofit2.Callback<UserList> {
            override fun onFailure(call: retrofit2.Call<UserList>, t: Throwable) {
                recyclerListData.postValue(null)
            }

            override fun onResponse(
                call: retrofit2.Call<UserList>,
                response: retrofit2.Response<UserList>
            ) {
                if(response.isSuccessful){
                    recyclerListData.postValue(response.body())
                }

            }


        })
    }



}
