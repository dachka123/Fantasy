@file:OptIn(ExperimentalFoundationApi::class)

package com.example.fantastika.SideBar.SideBarItem

import android.content.ClipData
import android.content.ClipDescription
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.fantastika.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SidebarItem(
    label: String,
    price: Int,
    onClick: () -> Unit,
    isUsed: Boolean,
    isDraggable: Boolean = true,
    onDragStart: () -> Unit = {}
) {
    val backgroundColor = if (isUsed) Color(0xFFD6D6D6) else Color(0xFFBDBDBD)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(Color.Transparent),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 8.dp)
                .then(
                    if(!isUsed && isDraggable){
                        Modifier.dragAndDropSource(block = {
                            detectTapGestures(
                                onLongPress = {
                                    //Close the sidebar immediately when dragging starts
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
                    }else{
                        Modifier.clickable(enabled = !isUsed) { onClick() }
                    }
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.imagelogo),
                contentDescription = "App Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                label,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "$$price",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFF1B5E20)
            )
        }
    }
}
