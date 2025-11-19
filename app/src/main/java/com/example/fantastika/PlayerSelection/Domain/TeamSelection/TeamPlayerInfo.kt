package com.example.fantastika.PlayerSelection.Domain.TeamSelection

data class TeamPlayerInfo(
    val playerId: String,
    val playerName: String,
    val playerPrice: Double,
    val playerPositionIndex: Int // Index 0-4
)
