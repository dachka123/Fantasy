package com.example.fantastika.LoginRegister.Domain

import com.example.fantastika.LoginRegister.Data.AuthRepository

class RegisterUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(username: String, email: String, password: String): Boolean {
        return repository.register(username, email, password)
    }
}