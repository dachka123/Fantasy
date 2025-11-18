package com.example.fantastika.LoginRegister.Domain

import com.example.fantastika.LoginRegister.Data.AuthRepository
import com.example.fantastika.LoginRegister.Data.AuthResult

class LoginUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(username: String, password: String): AuthResult {
        return repository.login(username, password)
    }
}