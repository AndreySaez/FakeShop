package com.example.coremodule

import androidx.fragment.app.FragmentManager

interface AppRouter {
    fun openProductsList(fragmentManager: FragmentManager)
}