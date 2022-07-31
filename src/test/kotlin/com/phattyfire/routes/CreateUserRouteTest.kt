package com.phattyfire.routes

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.phattyfire.data.models.User
import com.phattyfire.data.request.CreateAccountRequest
import com.phattyfire.data.responses.BasicApiResponse
import com.phattyfire.di.testModule
import com.phattyfire.plugins.configureSerialization
import com.phattyfire.repository.user.FakeUserRepository
import com.phattyfire.util.ApiResponseMessages
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.testing.*
import kotlinx.coroutines.runBlocking
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.BeforeTest
import kotlin.test.Test


internal class CreateUserRouteTest : KoinTest{

    private val userRepository by inject<FakeUserRepository>()
    private val gson = Gson()

    @BeforeTest
    fun setUp(){
        startKoin{
            modules(testModule)
        }
    }

    @Test
    fun `Create user, no body attached, responds with BadRequest` () = runBlocking{
        val user = User(
            email = "test1@test@.com",
            username = "test1",
            password = "test12",
            profileImageUrl = "",
            bio = ""
        )
        userRepository.createUser(user)
        withTestApplication(
            moduleFunction = {
                configureSerialization()
                install(Routing){
                    createUserRoute(userRepository)
                }
            }
        ){
            val request = handleRequest(
                method = HttpMethod.Post,
                uri = "/api/user/create"
            ){
                addHeader("Content-Type", "application/json")
                val request = CreateAccountRequest(
                    email = "test@test.com",
                    username = "test",
                    password = "asddwd"
                )
                setBody(gson.toJson(request))
            }

            val response = gson.fromJson(
                request.response.content ?: "",
                BasicApiResponse::class.java
            )
            assertThat(response.successful).isFalse()
            assertThat(response.message).isEqualTo(ApiResponseMessages.USER_ALREADY_EXISTS)

        }
    }
}