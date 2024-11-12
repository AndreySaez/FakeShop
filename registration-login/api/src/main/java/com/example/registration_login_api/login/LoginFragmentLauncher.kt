package com.example.registration_login_api.login

import androidx.fragment.app.FragmentManager

interface LoginFragmentLauncher {
    fun addLoginFragment(fragmentManager: FragmentManager)
    fun replaceLoginFragment(fragmentManager: FragmentManager)
}