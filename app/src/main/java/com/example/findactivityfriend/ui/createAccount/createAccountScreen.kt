package com.example.findactivityfriend.ui.createAccount

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.findactivityfriend.R
import com.google.android.material.textfield.TextInputLayout

class createAccountScreen : Fragment() {
    private lateinit var createAccountViewModel: CreateAccountViewModel;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_account_screen, container, false)



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createAccountViewModel = ViewModelProviders.of(this,CreateAccountViewModelFactory())
            .get(CreateAccountViewModel::class.java);
        val createMyAccount : Button = view.findViewById(R.id.create_account)
        val navController : NavController = Navigation.findNavController(view)
        val emailEditTextInputLayout = view.findViewById<TextInputLayout>(R.id.create_e_mail)
        val passwordEditTextInputLayout = view.findViewById<TextInputLayout>(R.id.create_password)
        val passwordVerifyEditTextInputLayout = view.findViewById<TextInputLayout>(R.id.create_password_verification)

        createAccountViewModel.createAccountResult.observe(viewLifecycleOwner , Observer { createAccountResult ->
            createAccountResult.success?.let {
                Log.d("success ",it.userId)
            }
            createAccountResult.error?.let {
                Log.d("errror ", it.toString())
            }
        })

        createMyAccount.setOnClickListener {
            createAccountViewModel.createAccount(emailEditTextInputLayout.editText?.text.toString(),
                passwordEditTextInputLayout.editText?.text.toString())
            navController.navigate(R.id.action_createAccountScreen_to_managerActivity)
        }
    }

}