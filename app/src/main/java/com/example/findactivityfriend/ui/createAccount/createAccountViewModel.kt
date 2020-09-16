package com.example.findactivityfriend.ui.createAccount

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findactivityfriend.R
import com.example.findactivityfriend.data.createAccount.CreateAccountRepository
import com.example.findactivityfriend.data.login.LoginRepository
import com.example.findactivityfriend.data.login.Result
import com.example.findactivityfriend.data.login.model.LoggedInUser
import com.example.findactivityfriend.ui.login.LoginResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateAccountViewModel(val createAccountRepository: CreateAccountRepository) : ViewModel(){
  private val _createAccountResult=MutableLiveData<LoginResult>()
  val createAccountResult :LiveData<LoginResult> =_createAccountResult


  fun createAccount(eMail: String , password : String){

    viewModelScope.launch(Dispatchers.IO) {
      val result  : Result<LoggedInUser> =  createAccountRepository.createAccount(eMail,password)
      if (result is Result.Success) {
        _createAccountResult.postValue(
          LoginResult(success =result.data))
      } else {
        _createAccountResult.postValue(LoginResult(error = R.string.login_failed))
      }

    }
  }
  fun isPasswordMatched(passOne:String,passTwo:String) : Boolean{
     return passOne==passTwo
  }

  fun isEmailValid(eMail : String) : Boolean{
    return if (eMail.contains("@")) {
      Patterns.EMAIL_ADDRESS.matcher(eMail).matches()
    } else {
      eMail.isNotBlank()
    }
  }


}

