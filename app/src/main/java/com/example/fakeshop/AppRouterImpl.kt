package com.example.fakeshop

import androidx.fragment.app.FragmentManager
import com.example.coremodule.AppRouter
import com.example.registartion_login.R
import com.example.registartion_login.registration.presentation.view.RegistrationFragment
import javax.inject.Inject

class AppRouterImpl @Inject constructor() : AppRouter {
    override fun openRegistration(fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction().addToBackStack(null)
            .add(R.id.main, RegistrationFragment()).commitAllowingStateLoss()
    }
}