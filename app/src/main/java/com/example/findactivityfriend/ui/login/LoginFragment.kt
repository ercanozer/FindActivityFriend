package com.example.findactivityfriend.ui.login

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

import com.example.findactivityfriend.R
import com.example.findactivityfriend.data.login.model.LoggedInUser
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private val callbackManager : CallbackManager = CallbackManager.Factory.create();
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProviders.of(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        val navController = Navigation.findNavController(view);


        val usernameEditText = view.findViewById<EditText>(R.id.username)
        val passwordEditText = view.findViewById<EditText>(R.id.password)
        val loginButton = view.findViewById<Button>(R.id.login)
        val loadingProgressBar = view.findViewById<ProgressBar>(R.id.loading)
        val createAccountButton = view.findViewById<Button>(R.id.createAccountBtn);
        val facebookLoginButton = view.findViewById<LoginButton>(R.id.singInWithFacebook)
        facebookLoginButton.setReadPermissions("email")
        facebookLoginButton.fragment = this

        loginViewModel.loginFormState.observe(viewLifecycleOwner,
            Observer { loginFormState ->
                if (loginFormState == null) {
                    return@Observer
                }
                loginButton.isEnabled = loginFormState.isDataValid
                loginFormState.usernameError?.let {
                    usernameEditText.error = getString(it)
                }
                loginFormState.passwordError?.let {
                    passwordEditText.error = getString(it)
                }
            })

        loginViewModel.loginResult.observe(viewLifecycleOwner,
            Observer { loginResult ->

                loginResult ?: return@Observer

                loadingProgressBar.visibility = View.GONE
                loginResult.error?.let {
                    showLoginFailed(it)
                }
                loginResult.success?.let {
                    updateUiWithUser(it,navController)
                }


            })

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
                loginViewModel.loginDataChanged(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
        }
        usernameEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.login(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
            false
        }

        loginButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            loginViewModel.login(
                usernameEditText.text.toString(),
                passwordEditText.text.toString()
            )
        }

        facebookLoginButton.setOnClickListener{
            loginWithFacebook()
        }

        createAccountButton.setOnClickListener{
            navController.navigate(R.id.action_loginFragment_to_createAccountScreen)
        }
    }

    private fun updateUiWithUser(model: LoggedInUser, navController: NavController) {


        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext,"${model.userId}", Toast.LENGTH_LONG).show()
        navController.navigate(R.id.action_loginFragment_to_managerActivity)

    }

    private fun showLoginFailed(@StringRes errorString: Int ) {

        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }
    private fun loginWithFacebook(){

        LoginManager.getInstance().registerCallback(callbackManager,object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult){
                loginViewModel.login(access_token = result.accessToken.token.toString())
                Log.d("facebookid", result.accessToken.token.toString())

            }

            override fun onCancel() {

            }

            override fun onError(error: FacebookException?) {

            }
        })
    }

     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         callbackManager.onActivityResult(requestCode,resultCode, data);

        super.onActivityResult(requestCode, resultCode, data)
    }

}