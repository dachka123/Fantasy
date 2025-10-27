package com.example.fantastika.PlayerSelection.DropZone.PlayerDetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.fantastika.R

@Composable
fun PlayerDetailsBottomDialog(
    playerName: String,
    playerPrice: Int,
    playerTeam: String,
    onDismiss: () -> Unit,
    onRemove: () -> Unit
) {
    var visible by remember { mutableStateOf(false) }
    var dragOffset by remember { mutableStateOf(0f) }

    LaunchedEffect(Unit) {
        visible = true
    }

    Dialog(
        onDismissRequest = {
            visible = false
            onDismiss()
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable {
                        visible = false
                        onDismiss()
                    })

            AnimatedVisibility(
                visible = visible,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(600)
                ),
                exit = slideOutVertically(
                    targetOffsetY = { it },
                    animationSpec = tween(600)
                )
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.8f)
                        .offset(y = (dragOffset / 2).dp)
                        .pointerInput(Unit) {
                            detectVerticalDragGestures(
                                onDragEnd = {
                                    if (dragOffset > 150) {
                                        visible = false
                                        onDismiss()
                                    }
                                    dragOffset = 0f
                                },
                                onVerticalDrag = { _, dragAmount ->
                                    val newOffset = dragOffset + dragAmount
                                    dragOffset = newOffset.coerceAtLeast(0f)
                                }
                            )
                        },
                    shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Box {
                        Image(
                            painter = painterResource(id = R.drawable.detailsbackground),
                            contentDescription = null,
                            modifier = Modifier.matchParentSize(),
                            contentScale = ContentScale.Crop
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .width(36.dp)
                                        .height(5.dp)
                                        .background(
                                            Color.Gray.copy(alpha = 0.3f),
                                            RoundedCornerShape(100.dp)
                                        )
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.imagelogo),
                                    contentDescription = "Player Image",
                                    modifier = Modifier
                                        .size(100.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )

                                Spacer(modifier = Modifier.width(16.dp))

                                Column(
                                    modifier = Modifier
                                        .padding(top = 10.dp, end = 45.dp)
                                        .weight(1f)
                                ) {
                                    Text(
                                        text = playerName,
                                        color = Color.White,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                    )

                                    Text(
                                        text = playerTeam,
                                        color = Color.Gray,
                                        fontSize = 14.sp
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "Price: $$playerPrice",
                                color = Color.White,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            )
                        }

                        IconButton(
                            onClick = {
                                visible = false
                                onDismiss()
                            },
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(25.dp)
                                .size(20.dp)
                                .background(Color.Gray, CircleShape)
                                .size(20.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun PlayerDetailsBottomDialogPrev() {
    PlayerDetailsBottomDialog(
        playerName = "Player Name",
        playerPrice = 100,
        onDismiss = {},
        onRemove = {},
        playerTeam = "Team Name"
    )
}