package com.example.fantastika.PlayerSelection.Domain

interface PlayerClient {
    suspend fun getPlayers(): List<SimplePlayer>
}