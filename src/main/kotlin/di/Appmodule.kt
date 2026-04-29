package com.example.di

import com.example.controller.UserController
import com.example.core.ApiHandler
import com.example.repository.UserRepository
import org.koin.dsl.module

val appModule = module {

    single { ApiHandler(get()) }
    single { UserRepository(get()) }
    single { UserController(get()) }
}