package com.phattyfire

import com.phattyfire.di.mainModule
import io.ktor.server.application.*
import com.phattyfire.plugins.*
import org.koin.ktor.plugin.Koin
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)




@Suppress("unused")
fun Application.module() {
    val mongoPw = System.getenv("MONGO_PW")
    val dbName = "phattyfire"
    val db = KMongo.createClient(
        connectionString = "mongodb+srv://Ziggy420:$mongoPw@cluster0.mg5w5x7.mongodb.net/$dbName?retryWrites=true&w=majority"
    ).coroutine
        .getDatabase(dbName)





    configureRouting()
    configureSockets()
    configureSerialization()
    configureMonitoring()
    configureHTTP()
    configureSecurity()
    install(Koin) {
        modules(mainModule)
    }
}

