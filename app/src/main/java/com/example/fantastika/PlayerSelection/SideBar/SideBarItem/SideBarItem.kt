@file:OptIn(ExperimentalFoundationApi::class)

package com.example.fantastika.PlayerSelection.SideBar.SideBarItem

import android.content.ClipData
import android.content.ClipDescription
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fantastika.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SidebarItem(
    label: String,
    price: Int,
    team: String,
    onClick: () -> Unit,
    isUsed: Boolean,
    isDraggable: Boolean = true,
    onDragStart: () -> Unit = {}
) {
    val backgroundColor = if (isUsed) Color(0xFFD6D6D6) else Color(0xF89D9797)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(backgroundColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(24.dp))
                .then(
                    if (!isUsed && isDraggable) {
                        Modifier.dragAndDropSource(block = {
                            detectTapGestures(
                                onLongPress = {
                                    onDragStart()
                                    startTransfer(
                                        transferData = DragAndDropTransferData(
                                            clipData = ClipData.newPlainText(
                                                ClipDescription.MIMETYPE_TEXT_PLAIN,
                                                label
                                            )
                                        )
                                    )
                                }
                            )
                        })
                    } else {
                        Modifier.clickable(enabled = !isUsed) { onClick() }
                    }
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.imagelogo),
                contentDescription = "Player Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(56.dp)
                    .height(65.dp)
                    .clip(RoundedCornerShape(topStart = 24.dp, bottomStart = 24.dp)) // Clip to match the card's left edge
                    .background(Color.Black)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp, horizontal = 5.dp)
            ) {
                Text(
                    label,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = team,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(end = 5.dp)
                )
                Text(
                    text = "$$price",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color(0xFF1B5E20)
                )
            }
        }
    }
}

@Preview
@Composable
private fun SidebarItemPreview() {
    SidebarItem(
        label = "Player Name",
        price = 10,
        team = "Team Name",
        onClick = {},
        isUsed = false,
        isDraggable = true,
        onDragStart = {}
    )
}
