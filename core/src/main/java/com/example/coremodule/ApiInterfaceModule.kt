package com.example.coremodule

import dagger.Module
import dagger.Provides

@Module
class ApiInterfaceModule {
    @Provides
    fun apiInterface() = ApiInterface.create()
}