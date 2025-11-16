package com.example.fantastika.PlayerSelection.Domain

data class SimplePlayer(
    val id: String,
    val name: String,
    val userPhoto: String?,
    val team: Team?,
    val price: Double
)

data class Team(
    val id: String,
    val name: String,
    val userPhoto: String?
)
