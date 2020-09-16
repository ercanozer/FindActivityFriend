package com.example.findactivityfriend.data.login

import android.util.Log
import com.example.findactivityfriend.data.login.model.LoggedInUser
import com.example.findactivityfriend.retrofit.RetrofitRequest
import java.io.IOException


class LoginDataSource {
   private val request = RetrofitRequest.retrofitInstance
    private val service = request?.create(LoginRequests::class.java)
    fun login(email: String, password: String): Result<LoggedInUser> {
        try {
            val call = service?.getLoginwithUsernameandPassword(email, password)?.execute()
            val user =call?.body()
            val jwtTokenFromHeader =call?.headers()?.get("x-auth-token").toString()
            user?.token = jwtTokenFromHeader
            Log.d("myToken" , jwtTokenFromHeader)
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

    fun loginWithFb(access_token:String):Result<LoggedInUser>{
        try {
            Log.d("asdasd",access_token)
            val call = service?.getLoginwithFbAuth(access_token)?.execute()?.body() as LoggedInUser

            return Result.Success(call)
        }catch (e:Throwable){
            Log.e("errorlogin", "hata", e)
            return Result.Error(IOException("Error logging in",e))
        }

    }


    fun logout() {
        // TODO: revoke authentication
    }
}