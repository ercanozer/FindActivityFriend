package com.example.findactivityfriend.data.login

import com.example.findactivityfriend.data.login.model.LoggedInUser
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface LoginRequests {
    @FormUrlEncoded
    @POST("auth/emailAndPassword")
    fun getLoginwithUsernameandPassword(
        @Field("eMail") name: String,
        @Field("password") password: String
    ): Call<LoggedInUser>


    @GET("auth/fbAuthentication")
    fun getLoginwithFbAuth(@Query("access_token") access_token:String) : Call<LoggedInUser>


}