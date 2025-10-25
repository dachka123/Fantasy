package com.example.fantastika.DropZone

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fantastika.SideBar.SideBarItem.SidebarItem
import com.example.fantastika.SideBar.SideBarItem.TeamItem
import com.example.fantastika.data.Player
import com.example.fantastika.data.allTeams

enum class DialogFilterMode {
    PLAYERS, TEAMS
}

@Composable
fun PlayerSelectionDialog(
    allPlayers: List<Player>,
    usedItems: List<String>,
    onDismiss: () -> Unit,
    onPlayerSelected: (Player) -> Unit
) {
    var filterMode by remember { mutableStateOf(DialogFilterMode.PLAYERS) }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        title = {
            Text(
                "Select ${if (filterMode == DialogFilterMode.PLAYERS) "Player" else "Team"}",
                style = MaterialTheme.typography.headlineSmall
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 450.dp)
            ) {
                // Filter Checkboxes
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Checkbox(
                            checked = filterMode == DialogFilterMode.PLAYERS,
                            onCheckedChange = { if (it) filterMode = DialogFilterMode.PLAYERS }
                        )
                        Text("Players", style = MaterialTheme.typography.bodyMedium)
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Checkbox(
                            checked = filterMode == DialogFilterMode.TEAMS,
                            onCheckedChange = { if (it) filterMode = DialogFilterMode.TEAMS }
                        )
                        Text("Teams", style = MaterialTheme.typography.bodyMedium)
                    }
                }

                HorizontalDivider(modifier = Modifier.padding(bottom = 8.dp))

                // Content based on filter
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, fill = false)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    when (filterMode) {
                        DialogFilterMode.PLAYERS -> {
                            allPlayers.forEach { player ->
                                val isUsed = usedItems.contains(player.name)

                                SidebarItem(
                                    label = player.name,
                                    price = player.price,
                                    team = player.team,
                                    isUsed = isUsed,
                                    isDraggable = false,
                                    onClick = {
                                        if (!isUsed) {
                                            onPlayerSelected(player)
                                        }
                                    }
                                )
                            }
                        }
                        DialogFilterMode.TEAMS -> {
                            allTeams.forEach { teamName ->
                                TeamItem(
                                    label = teamName,
                                    onClick = {
                                        // Optional: Handle team selection
                                        // Could show team details or filter by team
                                    }
                                )
                            }
                        }
                    }
                }
            }
        },
        containerColor = Color.White
    )
}