package com.example.fantastika.SideBar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fantastika.SideBar.SideBarItem.SidebarItem

@Composable
fun SidebarContent(
    usedItems: List<String>,
    onItemDragStart: () -> Unit
) {
    val players = listOf(
        "LebronLebronLebronLebronLebronLebron" to 1200,
        "Curry" to 1150,
        "Harden" to 900,
        "KD" to 1100,
        "AD" to 950,
        "MJ" to 2000,
        "Koby" to 1800
    )

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),

    ) {
        Text("Players", style = MaterialTheme.typography.headlineMedium)
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        players.forEach { (player,price) ->
            SidebarItem(
                label = player,
                price = price,
                isUsed = usedItems.contains(player),
                onClick = {},
                onDragStart = onItemDragStart,
                isDraggable = true
            )
        }
    }
}