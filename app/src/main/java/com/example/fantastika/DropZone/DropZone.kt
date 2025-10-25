package com.example.fantastika.DropZone

import android.content.ClipDescription
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.mimeTypes
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fantastika.DropZone.PlayerDetails.PlayerDetailsBottomDialog
import com.example.fantastika.data.allPlayers

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DropZone(
    droppedItem: String?,
    onItemDropped: (String) -> Unit,
    onItemRemoved: (String) -> Unit,
    usedItems: List<String>,
) {

    var showDialog by remember { mutableStateOf(false) }
    var showPlayerDetailsDialog by remember { mutableStateOf(false) }

    /*val allPlayers = listOf(
        Triple("LebronLebronLebronLebronLebronLebron", 1200, "Lakers"),
        Triple("Curry", 1150, "Warriors"),
        Triple("Harden", 900, "Nets"),
        Triple("Javakhishvili", 1100, "Mavericks"),
        Triple("AD", 950, "Lakers"),
        Triple("MJ", 2000, "Bulls"),
        Triple("Koby", 1800, "Lakers")
    )*/

    Box(
        modifier = Modifier
            .width(100.dp)
            .height(150.dp)
            .drawBehind {
                for (i in 1..2) {
                    drawRoundRect(
                        color = Color.White.copy(alpha = 0.3f / i),
                        cornerRadius = CornerRadius(12.dp.toPx()),
                        style = Stroke(width = (8.dp * i).toPx())
                    )
                }
            }
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 2.dp,
                color = Color.White,
                shape = RoundedCornerShape(12.dp)
            )
            /*.shadow(
                elevation = 16.dp,
                shape = RoundedCornerShape(12.dp),
                spotColor = Color.Green,
                ambientColor = Color.Red
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 3.dp,
                color = Color.Gray.copy(alpha = 0.3f),
                shape = RoundedCornerShape(12.dp)
            )*/
            .dragAndDropTarget(
                shouldStartDragAndDrop = { event ->
                    event.mimeTypes().contains(ClipDescription.MIMETYPE_TEXT_PLAIN)
                },
                target = remember {
                    object : DragAndDropTarget {
                        override fun onDrop(event: DragAndDropEvent): Boolean {
                            val label = event.toAndroidDragEvent()
                                .clipData?.getItemAt(0)?.text?.toString()
                            label?.let { onItemDropped(it) }
                            return true
                        }
                    }
                }
            )
            .clickable {
                if (droppedItem == null) {
                    showDialog = true
                } else {
                    showPlayerDetailsDialog = true
                }
            },
        contentAlignment = Alignment.Center
    ) {
        if (droppedItem != null) {
            val player = allPlayers.firstOrNull { it.name == droppedItem }
            Box(modifier = Modifier.fillMaxSize()) {
                PlayerCard(
                    playerName = droppedItem,
                    price = player?.price ?: 0
                )
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Remove item",
                    tint = Color.Red,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                        .size(24.dp)
                        .clickable { onItemRemoved(droppedItem) }
                )
            }
        } else {
            Text(
                text = "+",
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
    if (showDialog) {
        PlayerSelectionDialog(
            allPlayers = allPlayers,
            usedItems = usedItems,
            onDismiss = { showDialog = false },
            onPlayerSelected = { player ->
                onItemDropped(player.name)
                showDialog = false
            }
        )
    }
    if (showPlayerDetailsDialog && droppedItem != null) {
        val playerData = allPlayers.firstOrNull { it.name == droppedItem }

        PlayerDetailsBottomDialog(
            playerName = droppedItem,
            playerPrice = playerData?.price ?: 0,
            playerTeam = playerData?.team ?: "Free Agent",
            onDismiss = { showPlayerDetailsDialog = false },
            onRemove = {
                onItemRemoved(droppedItem)
                showPlayerDetailsDialog = false
            }
        )
    }
}