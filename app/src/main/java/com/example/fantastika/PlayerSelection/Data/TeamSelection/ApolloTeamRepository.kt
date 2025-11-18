package com.example.fantastika.PlayerSelection.Data.TeamSelection

import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Optional
import com.example.fantastika.AssignPlayerToTeamMutation
import com.example.fantastika.CreateTeamMutation
import com.example.fantastika.PlayerSelection.Domain.TeamSelection.TeamPlayerInfo
import com.example.fantastika.PlayerSelection.Domain.TeamSelection.UserTeam
import com.example.fantastika.UserTeamsQuery
import com.example.fantastika.type.CreateTeamInput
import jakarta.inject.Inject

class ApolloTeamRepository @Inject constructor(
    private val apolloClient: ApolloClient
): TeamRepository {

    override suspend fun saveUserTeam(userId: String, teamName: String, playerIds: List<String>): String {
        val existingTeam = loadUserTeam(userId)

        val teamId = if (existingTeam == null) {
            val createResponse = apolloClient.mutation(
                CreateTeamMutation(
                    input = CreateTeamInput(
                        name = teamName,
                        userId = Optional.present(userId)
                    )
                )
            ).execute()

            if (createResponse.hasErrors()) {
                throw Exception("Failed to create team: ${createResponse.errors?.joinToString()}")
            }
            createResponse.data?.createTeam?.id as String?
                ?: throw Exception("Failed to get team ID after creation.")
        } else {
            existingTeam.teamId
        }

        for (playerId in playerIds) {
            val assignResponse = apolloClient.mutation(
                AssignPlayerToTeamMutation(
                    teamId = teamId,
                    playerId = playerId
                )
            ).execute()

            if (assignResponse.hasErrors()) {
                Log.e("TeamRepo", "Failed to assign player $playerId: ${assignResponse.errors?.joinToString()}")
            }
        }

        return teamId
    }

    override suspend fun loadUserTeam(userId: String): UserTeam? {
        try {
            val response = apolloClient.query(UserTeamsQuery(userId)).execute()
            if (response.hasErrors()) {
                Log.e("TeamRepo", "GraphQL Errors: ${response.errors?.joinToString()}")
                throw Exception("Failed to load team.")
            }

            val teamData = response.data?.userTeams?.firstOrNull() ?: return null

            val players = teamData.players.mapIndexed { index, player ->
                TeamPlayerInfo(
                    playerId = player.id as String,
                    playerName = player.name,
                    playerPrice = player.price.toDouble(),
                    playerPositionIndex = index // This is a weak assumption, fix with API if possible.
                )
            }

            return UserTeam(
                teamId = teamData.id as String,
                name = teamData.name,
                players = players
            )
        } catch (e: Exception) {
            Log.e("TeamRepo", "Error loading user team: ${e.message}")
            return null
        }
    }
}
