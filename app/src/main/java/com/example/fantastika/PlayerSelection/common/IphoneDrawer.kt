package com.example.fantastika.PlayerSelection.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun IphoneDrawer(
    onDismiss: () -> Unit,
    heightFraction: Float = 0.8f,
    backgroundColor: Color = Color.White,
    dragThreshold: Float = 150f,
    cornerRadius: Dp = 12.dp,
    dimBackground: Boolean = true,
    content: @Composable () -> Unit
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
            // Dim background overlay
            if (dimBackground) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f))
                        .clickable {
                            visible = false
                            onDismiss()
                        }
                )
            }

            // Animated drawer card
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
                        .fillMaxHeight(heightFraction)
                        .offset(y = (dragOffset / 2).dp)
                        .pointerInput(Unit) {
                            detectVerticalDragGestures(
                                onDragEnd = {
                                    if (dragOffset > dragThreshold) {
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
                    shape = RoundedCornerShape(topStart = cornerRadius, topEnd = cornerRadius),
                    colors = CardDefaults.cardColors(containerColor = backgroundColor),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    content()
                }
            }
        }
    }
}

@Composable
fun DrawerDragHandle(
    modifier: Modifier = Modifier,
    color: Color = Color.Gray.copy(alpha = 0.3f)
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(36.dp)
                .height(5.dp)
                .background(color, RoundedCornerShape(100.dp))
        )
    }
}