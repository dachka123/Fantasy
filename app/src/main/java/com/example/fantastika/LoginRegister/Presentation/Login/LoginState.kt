package com.example.fantastika.LoginRegister.Presentation.Login

data class LoginState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,
    val accessToken: String? = null
)