package com.example.fantastika.PlayerSelection.PlayerSelectionSideBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fantastika.Common.Dimens
import com.example.fantastika.PlayerSelection.Common.FilterContent
import com.example.fantastika.PlayerSelection.Common.FilterMode
import com.example.fantastika.PlayerSelection.Common.FilterSection
import com.example.fantastika.PlayerSelection.Common.SortMode
import com.example.fantastika.PlayerSelection.Common.SortSection
import com.example.fantastika.PlayerSelection.Data.Player


@Composable
fun SidebarContent(
    allPlayers: List<Player>,
    usedItems: List<String>,
    onItemDragStart: () -> Unit
) {
    var filterMode by remember { mutableStateOf(FilterMode.PLAYERS) }
    var selectedTeam by remember { mutableStateOf<String?>(null) }
    var sortMode by remember { mutableStateOf(SortMode.NAME) }


    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(Dimens.spacing16)
            .fillMaxHeight()
    ) {
        Text(
            "Fantasy Basketball",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black
        )

        HorizontalDivider(modifier = Modifier.padding(vertical = Dimens.spacing8),color = Color.Black)

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
                    .padding(Dimens.spacing16)
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