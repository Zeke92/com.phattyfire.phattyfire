package com.phattyfire.repository.user

import com.phattyfire.data.models.User

    val users = mutableListOf<User>()

class FakeUserRepository : UserRepo {
    override suspend fun createUser(user: User) {
        users.add(user)    }

    override suspend fun getUserById(id: String): User? {
        return users.find { it.id == id }
    }

    override suspend fun getUserByEmail(email: String): User? {
        return users.find { it.email == email }
    }
}