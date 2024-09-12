package com.example.fakeshop.domain.registration

data class RegisterForm(
    val name: String,
    val email: String,
    val password: String,
    val cpassword: String,
)
