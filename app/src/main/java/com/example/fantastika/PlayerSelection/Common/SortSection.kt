package com.example.fantastika.PlayerSelection.Common

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fantastika.ui.theme.FantastikaTheme

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
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        onSortChange(
                            if (sortMode == SortMode.NAME) SortMode.PRICE else SortMode.NAME
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = FantastikaTheme.color.primary
                    ),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Sort")
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            imageVector = Icons.Default.SwapVert,
                            contentDescription = "Sort",
                            tint = FantastikaTheme.color.onPrimary
                        )
                    }
                }
            }
        }

        FilterMode.TEAMS -> {
            Modifier
        }
    }
}
