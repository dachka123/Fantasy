package com.example.fantastika.PlayerSelection.DropZone

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fantastika.Common.Dimens
import com.example.fantastika.PlayerSelection.Common.DrawerDragHandle
import com.example.fantastika.PlayerSelection.Common.FilterContent
import com.example.fantastika.PlayerSelection.Common.FilterMode
import com.example.fantastika.PlayerSelection.Common.FilterSection
import com.example.fantastika.PlayerSelection.Common.IphoneDrawer
import com.example.fantastika.PlayerSelection.Common.SortMode
import com.example.fantastika.PlayerSelection.Common.SortSection
import com.example.fantastika.PlayerSelection.Data.Player


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

    IphoneDrawer(
        onDismiss = onDismiss,
        heightFraction = 0.85f,
        backgroundColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.spacing20, vertical = Dimens.spacing16)
        ) {
            DrawerDragHandle(
                modifier = Modifier.padding(bottom = Dimens.spacing16)
            )

            Text(
                text = when (filterMode) {
                    FilterMode.PLAYERS -> "Select Player"
                    FilterMode.TEAMS -> "Select Team"
                    FilterMode.TEAM_PLAYERS -> selectedTeam ?: "Team Players"
                },
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = Dimens.spacing16),
                color = Color.Black
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

            HorizontalDivider(modifier = Modifier.padding(vertical = Dimens.spacing8))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                FilterContent(
                    modifier = Modifier.fillMaxSize(),
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

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(Dimens.spacing16)
                ) {
                    SortSection(
                        filterMode = filterMode,
                        sortMode = sortMode,
                        onSortChange = { sortMode = it }
                    )
                }
            }

            Spacer(modifier = Modifier.height(Dimens.spacing8))

            TextButton(
                onClick = onDismiss,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Cancel")
            }
        }
    }
}