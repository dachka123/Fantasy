package com.example.fantastika.PlayerSelection.Data.TeamSelection

import com.example.fantastika.PlayerSelection.Domain.TeamSelection.UserTeam

interface TeamRepository {
    suspend fun saveUserTeam(userId: String, teamName: String, playerIds: List<String>): String

    suspend fun loadUserTeam(userId: String): UserTeam?
}