package com.example.findactivityfriend.data.login.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("generalInformation")
    val generalInformation: GeneralInformation?,
    @SerializedName("message")
    val message : String,
    var token : String


)

data class GeneralInformation(
    @SerializedName("name")
    val name: String,
    @SerializedName("birthDate")
    val birthDate : Date,
    @SerializedName("gender")
    val gender : String ,
    @SerializedName("prefferedGenderForActivity")
    val prefferedGenderForActivity : String ,
    @SerializedName("aboutMe")
    val aboutMe : String ,
    @SerializedName("proffesion")
    val proffesion : String
)
