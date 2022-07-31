package com.phattyfire.repository.user

import com.phattyfire.data.models.User

interface UserRepo {

    suspend fun createUser(user: User)

    suspend fun getUserById(id: String): User?

    suspend fun getUserByEmail(email: String): User?
}