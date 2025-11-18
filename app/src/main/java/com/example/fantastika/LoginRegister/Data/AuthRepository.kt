package com.example.fantastika.LoginRegister.Data

interface AuthRepository {
    suspend fun login(username: String, password: String): AuthResult
    suspend fun register(username: String, email: String, password: String): Boolean
}