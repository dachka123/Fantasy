package com.example.fantastika.PlayerSelection.common

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SortSection(
    filterMode: FilterMode,
    sortMode: SortMode,
    onSortChange: (SortMode) -> Unit
) {
    when (filterMode) {
        FilterMode.PLAYERS, FilterMode.TEAM_PLAYERS -> {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { onSortChange(SortMode.NAME) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (sortMode == SortMode.NAME)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.surfaceVariant
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("By Name")
                }
                Button(
                    onClick = { onSortChange(SortMode.PRICE) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (sortMode == SortMode.PRICE)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.surfaceVariant
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("By Price")
                }
            }
        }

        FilterMode.TEAMS -> {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    fontSize = 20.sp,
                    text = "Select players from teams"
                )
            }
        }
    }
}
