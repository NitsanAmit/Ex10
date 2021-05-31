package com.namit.ex10.networking

import com.namit.ex10.networking.models.DataHolder
import com.namit.ex10.networking.models.SetPrettyNameRequest
import com.namit.ex10.networking.models.User
import retrofit2.Call
import retrofit2.http.*

interface UsersService {

    @GET("users/{user}/token")
    fun getToken(@Path("user") userName: String?): Call<DataHolder<String?>>

    @GET("user")
    fun getUser(@Header("Authorization") token: String): Call<DataHolder<User?>>

    @POST("user/edit/")
    fun editUser(
        @Header("Authorization") token: String,
        @Header("Content-Type") contentType: String,
        @Body request: SetPrettyNameRequest
    ): Call<DataHolder<User?>>

}