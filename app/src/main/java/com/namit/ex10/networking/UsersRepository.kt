package com.namit.ex10.networking

import com.namit.ex10.networking.models.DataHolder
import com.namit.ex10.networking.models.SetPrettyNameRequest
import com.namit.ex10.networking.models.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UsersRepository {

    private val retrofit: Retrofit
    private val usersService: UsersService

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client: OkHttpClient = OkHttpClient.Builder().build()
        retrofit = Retrofit.Builder()
            .baseUrl("https://hujipostpc2019.pythonanywhere.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        usersService = retrofit.create(UsersService::class.java)
    }

    fun getToken(userName: String): Call<DataHolder<String?>> {
        return usersService.getToken(userName)
    }

    fun getUser(token: String): Call<DataHolder<User?>> {
        return usersService.getUser("token " + token)
    }

    fun updatePrettyName(token: String, newName: String): Call<DataHolder<User?>> {
        return usersService.editUser("token " + token, "application/json", SetPrettyNameRequest(newName))
    }

}
