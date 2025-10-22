package com.example.fantastika.DropZone

import android.content.ClipDescription
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DropZone(
    droppedItem: String?,
    onItemDropped: (String) -> Unit,
    onItemRemoved: (String) -> Unit,
    usedItems: List<String>,
) {

    var showDialog by remember { mutableStateOf(false) }

    val allPlayers = listOf(
        "LeBronLeBronLeBronLeBron" to 1200,
        "Curry" to 1150,
        "Harden" to 900,
        "Javakhishvili" to 1100,
        "AD" to 950,
        "MJ" to 2000,
        "Koby" to 1800
    )

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
            .clickable(enabled = droppedItem == null) {
                showDialog = true
            },
        contentAlignment = Alignment.Center
    ) {
        if (droppedItem != null) {
            Box(modifier = Modifier.fillMaxSize()) {
                PlayerCard(
                    playerName = droppedItem,
                    price = allPlayers.firstOrNull { it.first == droppedItem }?.second ?: 0,
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
                onItemDropped(player)
                showDialog = false
            }
        )
    }
}