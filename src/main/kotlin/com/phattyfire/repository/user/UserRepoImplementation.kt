package com.phattyfire.repository.user

import com.phattyfire.data.models.User
import org.litote.kmongo.coroutine.CoroutineDatabase

class UserRepoImplementation(
     db: CoroutineDatabase
) : UserRepo {

    private val users = db.getCollection<User>()

    override suspend fun createUser(user: User) {
        users.insertOne(user)
    }

    override suspend fun getUserById(id: String): User? {
        return users.findOneById(id)
    }

    override suspend fun getUserByEmail(email: String): User? {
        return users.findOneById(email)
    }


}