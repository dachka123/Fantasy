package com.example.fantastika.PlayerSelection.DropZone

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fantastika.PlayerSelection.common.FilterContent
import com.example.fantastika.PlayerSelection.common.FilterMode
import com.example.fantastika.PlayerSelection.common.FilterSection
import com.example.fantastika.PlayerSelection.common.SortMode
import com.example.fantastika.PlayerSelection.common.SortSection
import com.example.fantastika.PlayerSelection.data.Player


@Composable
fun PlayerSelectionDialog(
    allPlayers: List<Player>,
    usedItems: List<String>,
    onDismiss: () -> Unit,
    onPlayerSelected: (Player) -> Unit
) {
    var filterMode by remember { mutableStateOf(FilterMode.PLAYERS) }
    var selectedTeam by remember { mutableStateOf<String?>(null) }
    var sortMode by remember { mutableStateOf(SortMode.NAME) }

    // Reset sort to NAME when changing filter modes
    /*LaunchedEffect(filterMode) {
        sortMode = SortMode.NAME
    }*/

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        title = {
            Text(
                when (filterMode) {
                    FilterMode.PLAYERS -> "Select Player"
                    FilterMode.TEAMS -> "Select Team"
                    FilterMode.TEAM_PLAYERS -> selectedTeam ?: "Team Players"
                },
                style = MaterialTheme.typography.headlineSmall
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 450.dp)
            ) {
                FilterSection(
                    filterMode = filterMode,
                    selectedTeam = selectedTeam,
                    onFilterChange = { filterMode = it },
                    onBackToTeams = {
                        filterMode = FilterMode.TEAMS
                        selectedTeam = null
                    }
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                SortSection(
                    filterMode = filterMode,
                    sortMode = sortMode,
                    onSortChange = { sortMode = it }
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                FilterContent(
                    filterMode = filterMode,
                    sortMode = sortMode,
                    allPlayers = allPlayers,
                    usedItems = usedItems,
                    selectedTeam = selectedTeam,
                    onPlayerSelected = onPlayerSelected,
                    onTeamSelected = {
                        selectedTeam = it
                        filterMode = FilterMode.TEAM_PLAYERS
                    }
                )

            }
        },
        containerColor = Color.White
    )
}