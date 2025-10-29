package com.example.fantastika.PlayerSelection.SideBar

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fantastika.PlayerSelection.common.FilterContent
import com.example.fantastika.PlayerSelection.common.FilterMode
import com.example.fantastika.PlayerSelection.common.FilterSection
import com.example.fantastika.PlayerSelection.common.SortMode
import com.example.fantastika.PlayerSelection.common.SortSection
import com.example.fantastika.PlayerSelection.data.Player


@Composable
fun SidebarContent(
    allPlayers: List<Player>,
    usedItems: List<String>,
    onItemDragStart: () -> Unit
) {
    var filterMode by remember { mutableStateOf(FilterMode.PLAYERS) }
    var selectedTeam by remember { mutableStateOf<String?>(null) }
    var sortMode by rememberSaveable { mutableStateOf(SortMode.NAME) }


    LaunchedEffect(filterMode) {
        sortMode = SortMode.PRICE
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxHeight()
    ) {
        Text("Fantasy Basketball", style = MaterialTheme.typography.headlineMedium)

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        FilterSection(
            filterMode = filterMode,
            selectedTeam = selectedTeam,
            onFilterChange = { filterMode = it },
            onBackToTeams = {
                filterMode = FilterMode.TEAMS
                selectedTeam = null
            }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            key(sortMode, filterMode, selectedTeam) {
                FilterContent(
                    modifier = Modifier.fillMaxSize(),
                    filterMode = filterMode,
                    sortMode = sortMode,
                    allPlayers = allPlayers,
                    usedItems = usedItems,
                    selectedTeam = selectedTeam,
                    onPlayerSelected = {},
                    onTeamSelected = {
                        selectedTeam = it
                        filterMode = FilterMode.TEAM_PLAYERS
                    },
                    isDraggable = true,
                    onDragStart = onItemDragStart
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                SortSection(
                    filterMode = filterMode,
                    sortMode = sortMode,
                    onSortChange = { sortMode = it }
                )
            }
        }
    }
}