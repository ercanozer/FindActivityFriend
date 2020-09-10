package com.example.findactivityfriend.data.login.model

import com.google.gson.annotations.SerializedName

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("displayName")
    val displayName: String
)