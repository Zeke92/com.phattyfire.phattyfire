package com.phattyfire.di

import com.phattyfire.controller.user.UserController
import com.phattyfire.controller.user.UserControllerImplementation
import com.phattyfire.data.models.User
import com.phattyfire.util.Constants
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo



val mainModule = module {
        single {
            val client = KMongo.createClient().coroutine
            client.getDatabase(Constants.DATABASE_NAME)
        }
    single<UserController> {
        UserControllerImplementation(get())

    }
}