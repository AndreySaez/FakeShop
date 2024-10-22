package com.example.coremodule

import dagger.Module
import dagger.Provides

@Module
class RetrofitModule {
    @Provides
    fun apiInterface() = ApiInterface.create()
}