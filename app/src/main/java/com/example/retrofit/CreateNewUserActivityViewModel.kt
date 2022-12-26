package com.example.retrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Response

class CreateNewUserActivityViewModel : ViewModel() {
    lateinit var createNewUserLiveData: MutableLiveData<UserResponse?>
    init {
        createNewUserLiveData  = MutableLiveData()
    }
    fun getCreateNewUserObservable() : MutableLiveData<UserResponse?>{
        return createNewUserLiveData
    }
    fun createUser(user:User){
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.createUser(user)
        call.enqueue(object : retrofit2.Callback<UserResponse?> {
            override fun onFailure(call: retrofit2.Call<UserResponse?>, t: Throwable) {
                createNewUserLiveData.postValue(null)
            }


            override fun onResponse(call: retrofit2. Call<UserResponse?>, response: Response<UserResponse?>)
            {
                if(response.isSuccessful){
                    createNewUserLiveData.postValue(response.body())
                }
                else{
                    createNewUserLiveData.postValue(null)
                }
            }


        })
    }
}