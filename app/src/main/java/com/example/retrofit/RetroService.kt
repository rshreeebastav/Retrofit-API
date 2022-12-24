package com.example.retrofit


import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RetroService {
//    https://gorest.co.in/public/v2/users
    @GET("users")
    @Headers ("Accept:application/json","Content-Type:application/json")
    fun getUsersList() : Call<UserList>

//    https://gorest.co.in/public/v2/users/aaa
//    https://gorest.co.in/public/v2/users?name=a
    @GET("users")
    @Headers ("Accept:application/json","Content-Type:application/json")
    fun searchUsers(@Query("name") searchText: String) : Call<UserList>

//For Edit
//    https://gorest.co.in/public/v2/users/5569
    @GET("users{user_id}")
    @Headers ("Accept:application/json","Content-Type:application/json")
    fun getUser(@Query("name") searchText: String) : Call<UserList>


    @POST("users")
    @Headers("Accept:application/json","Content-Type:application/json","Authorization: Bearer a13a552c3c29a2b7982a13a82792c2e11dc342c99969af1f3f2468e11d78c9ae")
    fun createUser(@Body params: User): Call<UserResponse>

//For Updating the data
    @PATCH("users/{user_id}")
    @Headers("Accept:application/json","Content-Type:application/json","Authorization: Bearer a13a552c3c29a2b7982a13a82792c2e11dc342c99969af1f3f2468e11d78c9ae")
    fun updateUser(@Path("user_id") user_id: String,@Body params: User) : Call<UserResponse>

    //For Updating the data
    @PATCH("users/{user_id}")
    @Headers("Accept:application/json","Content-Type:application/json","Authorization: Bearer a13a552c3c29a2b7982a13a82792c2e11dc342c99969af1f3f2468e11d78c9ae")
    fun deleteUser(@Path("user_id") user_id: String) : Call<UserResponse>
}