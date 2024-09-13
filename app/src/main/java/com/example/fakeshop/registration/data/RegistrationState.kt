package com.example.fakeshop.registration.data

import com.google.gson.annotations.SerializedName

data class RegistrationState(
    @SerializedName("status") val status: String
)