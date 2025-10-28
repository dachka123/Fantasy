package com.example.fantastika.PlayerSelection.DropZone

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fantastika.PlayerSelection.common.DrawerDragHandle
import com.example.fantastika.PlayerSelection.common.FilterContent
import com.example.fantastika.PlayerSelection.common.FilterMode
import com.example.fantastika.PlayerSelection.common.FilterSection
import com.example.fantastika.PlayerSelection.common.IphoneDrawer
import com.example.fantastika.PlayerSelection.common.SortMode
import com.example.fantastika.PlayerSelection.common.SortSection
import com.example.fantastika.PlayerSelection.data.Player


@Composable
fun PlayerSelectionDrawer(
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

    IphoneDrawer(
        onDismiss = onDismiss,
        heightFraction = 0.85f,
        backgroundColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            DrawerDragHandle(
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = when (filterMode) {
                    FilterMode.PLAYERS -> "Select Player"
                    FilterMode.TEAMS -> "Select Team"
                    FilterMode.TEAM_PLAYERS -> selectedTeam ?: "Team Players"
                },
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
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

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(
                onClick = onDismiss,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Cancel")
            }
        }
    }
}