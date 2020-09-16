package com.example.findactivityfriend.data.createAccount

import android.util.Log
import com.example.findactivityfriend.data.login.LoginRequests
import com.example.findactivityfriend.data.login.Result
import com.example.findactivityfriend.data.login.model.LoggedInUser
import com.example.findactivityfriend.retrofit.RetrofitRequest
import java.io.IOException


class CreateAccountDataSource() {
    fun createAccount(eMail: String, password: String) :Result<LoggedInUser>{
        val request = RetrofitRequest.retrofitInstance
        val service = request?.create(CreateAccountRequests::class.java)
        try {
            val call = service?.createAccount(eMail, password,"eMailandPassword")?.execute()
            val user =call?.body()
            val jwtTokenFromHeader =call?.headers()?.get("x-auth-token").toString()
            user?.token = jwtTokenFromHeader
            return Result.Success(call?.body() as LoggedInUser)
        } catch (e: Throwable) {
            Log.e("errorlogin", "hata", e)
            return Result.Error(
                IOException(
                    "Error logging in",
                    e
                )
            )
        }
    }

}