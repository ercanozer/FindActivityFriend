package com.example.findactivityfriend.ui.createAccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.findactivityfriend.data.createAccount.CreateAccountDataSource
import com.example.findactivityfriend.data.createAccount.CreateAccountRepository
import com.example.findactivityfriend.data.login.LoginRepository


class createAccountViewModelFactory : ViewModelProvider.Factory {

     override fun <T : ViewModel?> create(modelClass: Class<T>): T {
         TODO("Not yet implemented")
         if (modelClass.isAssignableFrom(CreateAccountViewModel::class.java)){
             return CreateAccountViewModel(createAccountRepository = CreateAccountRepository(CreateAccountDataSource())) as T
         }
         throw IllegalArgumentException("Unknown ViewModel class")
     }
}