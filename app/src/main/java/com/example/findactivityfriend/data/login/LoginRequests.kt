package com.example.findactivityfriend.data.login

import com.example.findactivityfriend.data.login.model.LoggedInUser
import kotlinx.coroutines.Deferred
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface LoginRequests {
    @FormUrlEncoded
    @POST("auth/login")
    fun getLogin(
        @Field("name") name: String,
        @Field("password") password: String
    ): Call<LoggedInUser>


}