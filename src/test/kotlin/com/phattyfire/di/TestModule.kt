package com.phattyfire.di

import com.phattyfire.repository.user.FakeUserRepository
import org.koin.dsl.module

internal val testModule = module {
    single{ FakeUserRepository() }
}