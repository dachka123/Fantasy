package com.example.fantastika.PlayerSelection.Domain.TeamSelection

import com.example.fantastika.PlayerSelection.Data.TeamSelection.TeamRepository
import jakarta.inject.Inject

class LoadTeamUseCase @Inject constructor(
    private val repository: TeamRepository
) {
    suspend fun execute(userId: String): UserTeam? {
        return repository.loadUserTeam(userId)
    }
}