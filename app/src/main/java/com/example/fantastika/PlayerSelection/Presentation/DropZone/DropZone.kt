package com.example.fantastika.PlayerSelection.Presentation.DropZone

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
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fantastika.Common.Dimens
import com.example.fantastika.PlayerSelection.Presentation.DropZone.PlayerDetails.PlayerDetailsBottomDialog
import com.example.fantastika.PlayerSelection.Presentation.PlayerSelectionSideBar.SideBarViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DropZone(
    droppedItem: String?,
    onItemDropped: (String) -> Boolean,
    onItemRemoved: (String) -> Unit,
    usedItems: List<String>,
    remainingBudget: Double
) {
    val viewModel: SideBarViewModel = hiltViewModel()
    val playersState by viewModel.playersState.collectAsStateWithLifecycle()
    val allPlayers = playersState.players

    val onBackground = Color.White
    var showDialog by remember { mutableStateOf(false) }
    var showPlayerDetailsDialog by remember { mutableStateOf(false) }
    var showBudgetError by remember { mutableStateOf(false) }

    fun getPlayerPrice(playerName: String): Int {
        return (allPlayers.firstOrNull { it.name == playerName }?.price ?: 0.0).toInt()
    }

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
                color = Color.Black,
                shape = RoundedCornerShape(Dimens.spacing12)
            )
            .border(
                width = 2.dp,
                color = Color.Black,
                shape = RoundedCornerShape(Dimens.spacing12)
            )
            .dragAndDropTarget(
                shouldStartDragAndDrop = { event ->
                    event.mimeTypes().contains(ClipDescription.MIMETYPE_TEXT_PLAIN)
                },
                target = remember {
                    object : DragAndDropTarget {
                        override fun onDrop(event: DragAndDropEvent): Boolean {
                            val playerName = event.toAndroidDragEvent()
                                .clipData?.getItemAt(0)?.text?.toString()

                            playerName?.let {
                                val success = onItemDropped(it)
                                if (!success && getPlayerPrice(it) > remainingBudget) {
                                    showBudgetError = true
                                }
                                return success
                            }
                            return false
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
            player?.let { simplePlayer ->
                Box(modifier = Modifier.fillMaxSize()) {
                    PlayerCard(
                        player = simplePlayer,
                        rating = 2
                    )
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Remove item",
                        tint = Color.Red,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(Dimens.spacing5)
                            .size(Dimens.spacing24)
                            .clickable { onItemRemoved(droppedItem) }
                    )
                }
            } ?: run {
                Text(
                    text = "Data Missing",
                    color = Color.White
                )
            }
        }
        else {
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
        PlayerSelectionDrawer(
            allPlayers = allPlayers,
            isLoading = playersState.isLoading,
            usedItems = usedItems,
            onDismiss = { showDialog = false },
            onPlayerSelected = { player ->
                val success = onItemDropped(player.name)

                // Check if the selection failed specifically due to budget constraint
                if (!success && player.price > remainingBudget) {
                    showBudgetError = true
                }
                showDialog = false
            }
        )
    }
    if (showBudgetError) {
        AlertDialog(
            /*modifier = Modifier
                .background(Color.Black),*/
            onDismissRequest = {
                showBudgetError = false
            },
            title = {
                Text(
                    "Budget Exceeded!",
                    color = Color.Red
                )
            },
            text = {
                Text(
                    "Your current remaining budget is $${remainingBudget}. " +
                            "Please select a cheaper player or remove an existing player from your lineup to free up funds.",
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showBudgetError = false
                    }
                ) {
                    Text(
                        "OK",
                        color = Color.Black
                    )
                }
            },
        )
    }
    if (showPlayerDetailsDialog && droppedItem != null) {
        val playerData = allPlayers.firstOrNull { it.name == droppedItem }

        PlayerDetailsBottomDialog(
            playerName = droppedItem,
            playerPrice = playerData?.price?.toInt() ?: 0, // Convert to Int for display
            playerTeam = playerData?.team?.name ?: "Free Agent", // Access team name
            onDismiss = { showPlayerDetailsDialog = false },
            onRemove = {
                onItemRemoved(droppedItem)
                showPlayerDetailsDialog = false
            }
        )
    }
}