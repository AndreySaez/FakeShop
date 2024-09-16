package com.example.fakeshop.login.data

import com.google.gson.annotations.SerializedName


data class LoginState(
    @SerializedName("status") val status: String,
    @SerializedName("token") val token: String
)