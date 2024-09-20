package com.example.fakeshop.login.data

import com.google.gson.annotations.SerializedName

data class ProfileState(
    @SerializedName("status") val status: String
)