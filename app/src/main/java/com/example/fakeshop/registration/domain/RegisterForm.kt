package com.example.fakeshop.registration.domain

data class RegisterForm(
    val name: String,
    val email: String,
    val password: String,
    val avatar: String = "https://picsum.photos/800"
)
