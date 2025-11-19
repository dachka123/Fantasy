package com.example.fantastika.PlayerSelection.Domain.TeamSelection

data class UserTeam(
    val teamId: String,
    val name: String,
    val players: List<TeamPlayerInfo>
)
