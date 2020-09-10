package com.example.findactivityfriend.data.login

import android.telecom.Call
import android.util.Log
import com.example.findactivityfriend.data.login.model.LoggedInUser
import com.example.findactivityfriend.retrofit.RetrofitRequest
import retrofit2.Response
import java.io.IOException
import java.util.*
import javax.security.auth.callback.Callback
import kotlin.math.log


class LoginDataSource {

    fun login(email: String, password: String): Result<LoggedInUser> {
        try {
            val request = RetrofitRequest.retrofitInstance
            val service = request?.create(LoginRequests::class.java)
            val call = service?.getLogin(email, password)?.execute()?.body()
            var result: LoggedInUser? = call
            val fakeUser = result as LoggedInUser

            return Result.Success(fakeUser)
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

    fun logout() {
        // TODO: revoke authentication
    }
}