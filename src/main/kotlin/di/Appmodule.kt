package com.example.di

import com.example.core.ApiHandler
import org.koin.dsl.module

val appModule = module {

    single { ApiHandler(get()) }

}