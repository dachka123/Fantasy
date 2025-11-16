package com.example.fantastika.PlayerSelection.Data

import com.example.fantastika.PlayerSelection.Domain.SimplePlayer
import com.example.fantastika.PlayerSelection.Domain.Team
import com.example.fantastika.PlayersQuery

fun PlayersQuery.Player.toSimplePlayer(): SimplePlayer {
    return SimplePlayer(
        id = id as String,
        name = name,
        userPhoto = userPhoto,

        team = Team(
            id = team?.id ?: "",
            name = team?.name ?: "",
            userPhoto = team?.userPhoto ?: ""
        ),

        price = price.toDouble()
    )
}