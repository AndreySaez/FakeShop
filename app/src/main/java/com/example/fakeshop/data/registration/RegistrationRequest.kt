package com.example.fakeshop.data.registration

import com.google.gson.annotations.SerializedName

data class RegistrationRequest(
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("cpassword") val confirmedPassword: String
)