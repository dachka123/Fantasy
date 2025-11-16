package com.example.fantastika.PlayerSelection.Domain

class GetPlayersUseCase(
    private val playerClient: PlayerClient
) {

    suspend fun execute(): List<SimplePlayer>{
        return playerClient
            .getPlayers()
            .sortedBy { it.name }
    }
}