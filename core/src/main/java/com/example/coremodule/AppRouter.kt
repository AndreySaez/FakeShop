package com.example.coremodule

import androidx.fragment.app.FragmentManager

interface AppRouter {
    fun openRegistration(fragmentManager: FragmentManager)
    fun openProductsList(fragmentManager: FragmentManager)
}