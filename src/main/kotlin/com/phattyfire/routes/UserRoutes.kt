package com.phattyfire.routes

import com.phattyfire.controller.user.UserController
import com.phattyfire.data.models.User
import com.phattyfire.data.request.CreateAccountRequest
import com.phattyfire.data.responses.BasicApiResponse
import com.phattyfire.util.ApiResponseMessages.FIELDS_BLANK
import com.phattyfire.util.ApiResponseMessages.USER_ALREADY_EXISTS
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Route.userRoutes(){

    val userController: UserController by inject()
    route("/api/user/create"){
        post {
            val request = call.receiveOrNull<CreateAccountRequest>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }
            val userExist = userController.getUserByEmail(request.email) != null
            if (userExist){
                    call.respond(
                        BasicApiResponse(
                            successful = false,
                            message = USER_ALREADY_EXISTS
                        )
                    )
                return@post
            }
            if (request.email.isBlank() || request.password.isBlank()|| request.username.isBlank()){
                call.respond(
                    BasicApiResponse(
                        successful = false,
                        message = FIELDS_BLANK)
                )
                return@post
            }
            userController.createUser(
                User(
                    email = request.email,
                    username = request.username,
                    password = request.password,
                    skills = "",
                    bio = "",
                    profileImageUrl = ""
                )
            )
            call.respond(
                BasicApiResponse(successful = true)
            )
        }
    }
}