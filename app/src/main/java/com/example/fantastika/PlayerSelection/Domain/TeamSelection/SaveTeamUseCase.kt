package com.example.fantastika.PlayerSelection.Domain.TeamSelection

import android.util.Log
import com.example.fantastika.PlayerSelection.Data.TeamSelection.TeamRepository
import com.example.fantastika.PlayerSelection.Domain.SimplePlayer
import jakarta.inject.Inject

class SaveTeamUseCase @Inject constructor(
    private val repository: TeamRepository
) {
    suspend fun execute(userId: String, teamName: String, selectedPlayers: List<SimplePlayer>): Boolean {

        if (selectedPlayers.size != 5) {
            Log.e("SaveTeamUC", "Validation failed: Exactly 5 players must be selected.")
            return false
        }

        val playerIds = selectedPlayers.map { it.id }

        return try {
            val teamId = repository.saveUserTeam(
                userId = userId,
                teamName = teamName,
                playerIds = playerIds
            )
            Log.d("SaveTeamUC", "Team saved successfully with ID: $teamId")
            true
        } catch (e: Exception) {
            Log.e("SaveTeamUC", "Error saving team: ${e.message}", e)
            false
        }
    }
}