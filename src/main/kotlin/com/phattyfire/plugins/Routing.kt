package com.phattyfire.plugins

import com.phattyfire.repository.user.UserRepo
import com.phattyfire.routes.createUserRoute
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val userRepository: UserRepo by inject()
    routing {
        createUserRoute(userRepository)
    }
}
