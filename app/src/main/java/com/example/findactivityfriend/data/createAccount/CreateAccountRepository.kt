package com.example.findactivityfriend.data.createAccount

import com.example.findactivityfriend.data.login.Result
import com.example.findactivityfriend.data.login.model.LoggedInUser

class CreateAccountRepository(val createAccountDataSource: CreateAccountDataSource) {

    fun createAccount (eMail : String , password : String ) : Result<LoggedInUser> {
       return createAccountDataSource.createAccount(eMail,password)
    }

}