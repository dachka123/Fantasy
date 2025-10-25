package com.example.fantastika.SideBar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fantastika.SideBar.SideBarItem.SidebarItem
import com.example.fantastika.SideBar.SideBarItem.TeamItem
import com.example.fantastika.data.allPlayers
import com.example.fantastika.data.allTeams

enum class FilterMode {
    PLAYERS, TEAMS
}

@Composable
fun SidebarContent(
    usedItems: List<String>,
    onItemDragStart: () -> Unit
) {

    var filterMode by remember { mutableStateOf(FilterMode.PLAYERS) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),

        ) {
        Text("Choose Players", style = MaterialTheme.typography.headlineMedium)
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Checkbox(
                    checked = filterMode == FilterMode.PLAYERS,
                    onCheckedChange = { if (it) filterMode = FilterMode.PLAYERS }
                )
                Text("Players", style = MaterialTheme.typography.bodyMedium)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Checkbox(
                    checked = filterMode == FilterMode.TEAMS,
                    onCheckedChange = { if (it) filterMode = FilterMode.TEAMS }
                )
                Text("Teams", style = MaterialTheme.typography.bodyMedium)
            }
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        when (filterMode) {
            FilterMode.PLAYERS -> {
                allPlayers.forEach { player ->
                    SidebarItem(
                        label = player.name,
                        price = player.price,
                        team = player.team,
                        isUsed = usedItems.contains(player.name),
                        onClick = {},
                        onDragStart = onItemDragStart,
                        isDraggable = true
                    )
                }
            }

            FilterMode.TEAMS -> {
                allTeams.forEach { teamName ->
                    TeamItem(
                        label = teamName,
                        onClick = {
                            // Optional: Handle team click to show team details
                        }
                    )
                }
            }
        }
    }
}