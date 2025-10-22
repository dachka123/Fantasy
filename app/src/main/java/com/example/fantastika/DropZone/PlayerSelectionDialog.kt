package com.example.fantastika.DropZone

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fantastika.SideBar.SideBarItem.SidebarItem

@Composable
fun PlayerSelectionDialog(
    allPlayers: List<Pair<String, Int>>,
    usedItems: List<String>,
    onDismiss: () -> Unit,
    onPlayerSelected: (String) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {},
        title = { Text("Select Player", style = MaterialTheme.typography.headlineSmall) },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 350.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                allPlayers.forEach { (player, price) ->
                    val isUsed = usedItems.contains(player)

                    SidebarItem(
                        label = player,
                        price = price,
                        isUsed = isUsed,
                        isDraggable = false,
                        onClick = { onPlayerSelected(player) }
                    )
                }
            }
        },
        containerColor = Color.White
    )
}