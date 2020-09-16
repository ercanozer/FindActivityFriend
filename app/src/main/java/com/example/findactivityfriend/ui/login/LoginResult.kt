package com.example.findactivityfriend.ui.login

import com.example.findactivityfriend.data.login.model.LoggedInUser

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: LoggedInUser? = null,
    val error: Int? = null
)