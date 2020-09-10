package com.example.findactivityfriend.ui.createAccount

import androidx.lifecycle.ViewModel
import com.example.findactivityfriend.data.createAccount.CreateAccountRepository
import com.example.findactivityfriend.data.login.LoginRepository

class CreateAccountViewModel(val createAccountRepository: CreateAccountRepository) : ViewModel(){


  fun createAccount(){

  }

  fun createAccountDataChanged(email : String , password : String ,passwordConfirmation : String ){

  }

  fun isPasswordMatched(){

  }

  fun isEmailValid(){

  }


}

