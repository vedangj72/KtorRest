package com.example.di

import com.example.controller.UserController
import com.example.core.ApiHandler
import com.example.repository.UserRepository
import org.koin.dsl.module

val appModule = module {

    single<ApiHandler> { ApiHandler(get()) }
    single<UserRepository> { UserRepository(get()) }
    single<UserController> { UserController(get()) }
}
