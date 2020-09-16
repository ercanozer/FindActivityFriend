package com.example.findactivityfriend.data.createAccount

import com.example.findactivityfriend.data.login.model.LoggedInUser
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface CreateAccountRequests {
    @FormUrlEncoded
    @POST("auth/createAccount")
    fun createAccount(
        @Field("eMail") eMail: String
        , @Field("password") password: String
        , @Field("accountType") accountType: String
    ): Call<LoggedInUser>
}