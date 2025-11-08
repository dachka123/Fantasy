package com.example.fantastika.PlayerSelection.DropZone

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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fantastika.Common.Dimens
import com.example.fantastika.PlayerSelection.DropZone.PlayerDetails.PlayerDetailsBottomDialog
import com.example.fantastika.PlayerSelection.Data.allPlayers
import com.example.fantastika.ui.theme.FantastikaTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DropZone(
    droppedItem: String?,
    onItemDropped: (String) -> Unit,
    onItemRemoved: (String) -> Unit,
    usedItems: List<String>,
) {

    val onBackground = FantastikaTheme.color.onBackground
    var showDialog by remember { mutableStateOf(false) }
    var showPlayerDetailsDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .width(Dimens.spacing100)
            .height(Dimens.spacing150)
            .drawBehind {
                for (i in 1..2) {
                    drawRoundRect(
                        color = onBackground.copy(alpha = 0.3f / i),
                        cornerRadius = CornerRadius(Dimens.spacing12.toPx()),
                        style = Stroke(width = (8.dp * i).toPx())
                    )
                }
            }
            .background(
                color = FantastikaTheme.color.background,
                shape = RoundedCornerShape(Dimens.spacing12)
            )
            .border(
                width = 2.dp,
                color = FantastikaTheme.color.onBackground,
                shape = RoundedCornerShape(Dimens.spacing12)
            )
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
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(Dimens.spacing5)
                        .size(Dimens.spacing24)
                        .clickable { onItemRemoved(droppedItem) }
                )
            }
        } else {
            Text(
                text = "+",
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                color = FantastikaTheme.color.onBackground,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
    if (showDialog) {
        PlayerSelectionDrawer(
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