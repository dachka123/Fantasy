package com.example.fantastika.PlayerSelection.Data

import com.apollographql.apollo.ApolloClient
import com.example.fantastika.PlayerSelection.Domain.PlayerClient
import com.example.fantastika.PlayerSelection.Domain.SimplePlayer
import com.example.fantastika.PlayersQuery

class ApolloPlayerClient(
    private val apolloClient: ApolloClient
): PlayerClient {

    override suspend fun getPlayers(): List<SimplePlayer> {
        return apolloClient
            .query(PlayersQuery())
            .execute()
            .data
            ?.players
            ?.map{ it.toSimplePlayer() }
            ?: emptyList()
    }
}