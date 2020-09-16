package com.example.findactivityfriend.ui.createAccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.findactivityfriend.data.createAccount.CreateAccountDataSource
import com.example.findactivityfriend.data.createAccount.CreateAccountRepository
import com.example.findactivityfriend.data.login.LoginRepository


class CreateAccountViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
     override fun <T : ViewModel?> create(modelClass: Class<T>): T {
         if (modelClass.isAssignableFrom(CreateAccountViewModel::class.java)){
             return CreateAccountViewModel(createAccountRepository = CreateAccountRepository(CreateAccountDataSource())) as T
         }
         throw IllegalArgumentException("Unknown ViewModel class")
     }
}