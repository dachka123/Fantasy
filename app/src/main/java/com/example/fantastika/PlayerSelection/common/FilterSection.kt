package com.example.fantastika.PlayerSelection.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FilterSection(
    filterMode: FilterMode,
    selectedTeam: String?,
    onFilterChange: (FilterMode) -> Unit,
    onBackToTeams: () -> Unit
) {
    if (filterMode == FilterMode.TEAM_PLAYERS) {
        // Back Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackToTeams) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back to Teams"
                )
            }
            Text(
                text = selectedTeam ?: "Back to Teams",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    } else {
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
                    checked = filterMode == FilterMode.PLAYERS,
                    onCheckedChange = { if (it) onFilterChange(FilterMode.PLAYERS) }
                )
                Text("Players", style = MaterialTheme.typography.bodyMedium)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Checkbox(
                    checked = filterMode == FilterMode.TEAMS,
                    onCheckedChange = { if (it) onFilterChange(FilterMode.TEAMS) }
                )
                Text("Teams", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}


