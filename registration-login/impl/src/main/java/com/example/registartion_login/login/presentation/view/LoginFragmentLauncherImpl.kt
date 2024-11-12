package com.example.registartion_login.login.presentation.view

import androidx.fragment.app.FragmentManager
import com.example.registartion_login.R
import com.example.registration_login_api.login.LoginFragmentLauncher
import javax.inject.Inject

class LoginFragmentLauncherImpl @Inject constructor() : LoginFragmentLauncher {
    override fun addLoginFragment(fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .add(R.id.main, LoginFragment())
            .commitAllowingStateLoss()
    }

    override fun replaceLoginFragment(fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.main, LoginFragment())
            .commit()
    }
}