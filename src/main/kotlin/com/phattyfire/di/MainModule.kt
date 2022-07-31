package com.phattyfire.di

import com.phattyfire.repository.user.UserRepo
import com.phattyfire.repository.user.UserRepoImplementation
import com.phattyfire.util.Constants
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo



val mainModule = module {
        single {
            val client = KMongo.createClient().coroutine
            client.getDatabase(Constants.DATABASE_NAME)
        }
    single<UserRepo> {
        UserRepoImplementation(get())

    }
}