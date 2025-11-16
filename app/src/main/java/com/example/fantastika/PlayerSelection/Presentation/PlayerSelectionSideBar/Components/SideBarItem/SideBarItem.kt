@file:OptIn(ExperimentalFoundationApi::class)

package com.example.fantastika.PlayerSelection.Presentation.PlayerSelectionSideBar.Components.SideBarItem

import android.content.ClipData
import android.content.ClipDescription
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import com.example.fantastika.Common.Dimens
import com.example.fantastika.PlayerSelection.Domain.SimplePlayer

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SidebarItem(
    onClick: () -> Unit,
    isUsed: Boolean,
    isDraggable: Boolean = true,
    onDragStart: () -> Unit = {},
    player: SimplePlayer
) {
    val backgroundColor = if (isUsed) Color.White else Color(0xFFB9B9BE)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.spacing10)
            .clip(RoundedCornerShape(Dimens.spacing24))
            .background(backgroundColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(Dimens.spacing1, Color.Black, RoundedCornerShape(Dimens.spacing24))
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
                                                player.name
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
            AsyncImage(
                model = player.userPhoto,
                contentDescription = "Player Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(Dimens.spacing56)
                    .height(Dimens.spacing70)
                    .clip(RoundedCornerShape(topStart = Dimens.spacing24, bottomStart = Dimens.spacing24))
            )
            Spacer(modifier = Modifier.width(Dimens.spacing24))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Dimens.spacing24, horizontal = Dimens.spacing5)
            ) {
                Text(
                    player.name,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f),
                    color = Color.Black
                )
                Text(
                    text = player.team?.name ?: "N/A",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(end = Dimens.spacing5),
                    color = Color.Black
                )
                Text(
                    text = "$${String.format("%.2f", player.price)}",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black
                )
            }
        }
    }
}

/*@Preview
@Composable
private fun SidebarItemPreview() {
    SidebarItem(
        onClick = {},
        isUsed = false,
        isDraggable = true,
        onDragStart = {},
        player = SimplePlayer(
            name = "dachi"

        )
    )
}*/
