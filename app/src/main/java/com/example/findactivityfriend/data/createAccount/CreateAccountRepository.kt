package com.example.findactivityfriend.data.createAccount

class CreateAccountRepository(val createAccountDataSource: CreateAccountDataSource) {

    fun createAccount (eMail : String , password : String ){
        createAccountDataSource.createAccount(eMail,password)
    }

}