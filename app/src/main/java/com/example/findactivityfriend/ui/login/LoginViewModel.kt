package com.example.findactivityfriend.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.example.findactivityfriend.data.login.LoginRepository
import com.example.findactivityfriend.data.login.Result


import com.example.findactivityfriend.R
import com.example.findactivityfriend.data.login.model.LoggedInUser

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult


    fun login(username: String = "", password: String = "" , access_token : String = "") {
        viewModelScope.launch(Dispatchers.IO) {
            val result  :Result<LoggedInUser>
            if (access_token==""){
                 result = loginRepository.login(username, password)
            }else{
                result = loginRepository.login(accessToken= access_token)

            }
            if (result is Result.Success) {
                _loginResult.postValue(
                    LoginResult(success =result.data))
            } else {
                _loginResult.postValue(LoginResult(error = R.string.login_failed))
            }

        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }




}