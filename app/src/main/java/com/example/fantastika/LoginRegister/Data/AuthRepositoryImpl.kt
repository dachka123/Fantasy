package com.example.fantastika.LoginRegister.Data

import com.apollographql.apollo.ApolloClient
import com.example.fantastika.LoginMutation
import com.example.fantastika.RegisterMutation
import com.example.fantastika.type.LoginInput
import com.example.fantastika.type.RegisterInput

class AuthRepositoryImpl(
    private val apolloClient: ApolloClient
): AuthRepository {

    override suspend fun login(username: String, password: String): AuthResult {
        val response = apolloClient.mutation(
            LoginMutation(
                input = LoginInput(username = username, password = password)
            )
        ).execute()

        if (response.hasErrors()) {
            val errorMessage = response.errors?.firstOrNull()?.message ?: "Login failed due to server error."
            throw Exception(errorMessage)
        }

        val token = response.data?.login?.access_token
        val user = response.data?.login?.user

        if (token == null || user == null) {
            throw Exception("Login failed: Invalid credentials or token missing.")
        }

        //Return the token AND the username
        return AuthResult(accessToken = token, username = user.username)
    }

    override suspend fun register(username: String, email: String, password: String): Boolean {
        val response = apolloClient.mutation(
            RegisterMutation(
                input = RegisterInput(
                    username = username,
                    email = email,
                    password = password
                )
            )
        ).execute()

        if (response.hasErrors()) {
            val errorMessage = response.errors?.firstOrNull()?.message ?: "Registration failed due to server error."
            throw Exception(errorMessage)
        }

        return response.data?.register != null
    }
}