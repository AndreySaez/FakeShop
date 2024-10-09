package com.example.fakeshop.login.data

import com.google.gson.annotations.SerializedName


data class LoginState(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String
)