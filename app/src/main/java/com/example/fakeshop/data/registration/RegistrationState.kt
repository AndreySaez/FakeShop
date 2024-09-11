package com.example.fakeshop.data.registration

import com.google.gson.annotations.SerializedName

data class RegistrationState(
    @SerializedName("status") val status: String
)