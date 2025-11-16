package com.example.fantastika.PlayerSelection.Presentation.Common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment
import com.example.fantastika.Common.Dimens
import com.example.fantastika.PlayerSelection.Presentation.PlayerSelectionSideBar.Components.SideBarItem.SidebarItem
import com.example.fantastika.PlayerSelection.Presentation.PlayerSelectionSideBar.Components.SideBarItem.TeamItem
import com.example.fantastika.PlayerSelection.Domain.SimplePlayer

@Composable
fun FilterContent(
    modifier: Modifier = Modifier,
    filterMode: FilterMode,
    sortMode: SortMode,
    allPlayers: List<SimplePlayer>,
    usedItems: List<String>,
    isLoading: Boolean,
    selectedTeam: String?,
    onPlayerSelected: (SimplePlayer) -> Unit,
    onTeamSelected: (String) -> Unit,
    isDraggable: Boolean = false,
    onDragStart: () -> Unit = {},
) {

    if(isLoading){
        Box(
            modifier.fillMaxSize()
        ){
            CircularProgressIndicator(
                modifier
                    .align(Alignment.Center)
                    .size(Dimens.spacing24)
            )
        }

    }
    else {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(Dimens.spacing8)
        ) {
            when (filterMode) {
                FilterMode.PLAYERS -> {
                    val sortedPlayers = when (sortMode) {
                        SortMode.NAME -> allPlayers.sortedBy { it.name }
                        SortMode.PRICE -> allPlayers.sortedByDescending { it.price }
                    }

                    sortedPlayers.forEach { player ->
                        val isUsed = usedItems.contains(player.name)
                        SidebarItem(
                            player = player,
                            isUsed = isUsed,
                            isDraggable = isDraggable,
                            onClick = {
                                if (!isUsed) onPlayerSelected(player)
                            },
                            onDragStart = onDragStart
                        )
                    }
                }

                FilterMode.TEAMS -> {
                    val uniqueTeams = allPlayers
                        .mapNotNull { it.team?.name }
                        .distinct()

                    val sortedTeams = remember(uniqueTeams) { uniqueTeams.sorted() }
                    sortedTeams.forEach { teamName ->
                        TeamItem(
                            label = teamName,
                            onClick = { onTeamSelected(teamName) }
                        )
                    }
                }

                FilterMode.TEAM_PLAYERS -> {
                    val teamPlayers = allPlayers.filter { it.team?.name == selectedTeam }
                    val sortedTeamPlayers = when (sortMode) {
                        SortMode.NAME -> teamPlayers.sortedBy { it.name }
                        SortMode.PRICE -> teamPlayers.sortedByDescending { it.price }
                    }
                    sortedTeamPlayers.forEach { player ->
                        val isUsed = usedItems.contains(player.name)
                        SidebarItem(
                            player = player,
                            isUsed = isUsed,
                            isDraggable = isDraggable,
                            onClick = {
                                if (!isUsed) onPlayerSelected(player)
                            },
                            onDragStart = onDragStart
                        )
                    }
                }
            }
        }
    }
}
