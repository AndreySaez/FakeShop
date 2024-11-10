package com.example.registartion_login.registration.presentation.view

import androidx.fragment.app.FragmentManager
import com.example.registartion_login.R
import com.example.registration_login_api.registration.RegistrationFragmentLauncher
import javax.inject.Inject

class RegistrationFragmentLauncherImpl @Inject constructor() : RegistrationFragmentLauncher {
    override fun openRegistration(fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction().addToBackStack(null)
            .add(R.id.main, RegistrationFragment()).commitAllowingStateLoss()
    }
}