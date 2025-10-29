package com.example.fantastika.PlayerSelection.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.unit.dp
import com.example.fantastika.PlayerSelection.SideBar.SideBarItem.SidebarItem
import com.example.fantastika.PlayerSelection.SideBar.SideBarItem.TeamItem
import com.example.fantastika.PlayerSelection.data.Player
import com.example.fantastika.PlayerSelection.data.allTeams

@Composable
fun FilterContent(
    modifier: Modifier = Modifier,
    filterMode: FilterMode,
    sortMode: SortMode,
    allPlayers: List<Player>,
    usedItems: List<String>,
    selectedTeam: String?,
    onPlayerSelected: (Player) -> Unit,
    onTeamSelected: (String) -> Unit,
    isDraggable: Boolean = false,
    onDragStart: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
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
                        label = player.name,
                        price = player.price,
                        team = player.team,
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
                val sortedTeams = remember { allTeams.sorted() }
                sortedTeams.forEach { teamName ->
                    TeamItem(
                        label = teamName,
                        onClick = { onTeamSelected(teamName) }
                    )
                }
            }

            FilterMode.TEAM_PLAYERS -> {
                val teamPlayers = allPlayers.filter { it.team == selectedTeam }
                val sortedTeamPlayers = when (sortMode) {
                    SortMode.NAME -> teamPlayers.sortedBy { it.name }
                    SortMode.PRICE -> teamPlayers.sortedByDescending { it.price }
                }
                sortedTeamPlayers.forEach { player ->
                    val isUsed = usedItems.contains(player.name)
                    SidebarItem(
                        label = player.name,
                        price = player.price,
                        team = player.team,
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
