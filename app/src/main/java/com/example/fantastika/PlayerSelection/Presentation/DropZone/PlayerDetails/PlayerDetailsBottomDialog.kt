package com.example.fantastika.PlayerSelection.Presentation.DropZone.PlayerDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.fantastika.Common.Dimens
import com.example.fantastika.PlayerSelection.Presentation.Common.DrawerDragHandle
import com.example.fantastika.PlayerSelection.Presentation.Common.IphoneDrawer
import com.example.fantastika.R

@Composable
fun PlayerDetailsBottomDialog(
    playerName: String,
    playerPrice: Int,
    playerTeam: String,
    onDismiss: () -> Unit,
    onRemove: () -> Unit
) {
    IphoneDrawer(
        onDismiss = onDismiss,
        heightFraction = 0.8f,
        //backgroundColor = FantastikaTheme.color.background
        backgroundColor = Color.White
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
                    .padding(Dimens.spacing20)
            ) {
                DrawerDragHandle(
                    modifier = Modifier.padding(vertical = Dimens.spacing8)
                )

                Spacer(modifier = Modifier.height(Dimens.spacing8))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.imagelogo),
                        contentDescription = "Player Image",
                        modifier = Modifier
                            .size(Dimens.spacing100)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(Dimens.spacing16))

                    Column(
                        modifier = Modifier
                            .padding(top = Dimens.spacing10, end = Dimens.spacing45)
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

                Spacer(modifier = Modifier.height(Dimens.spacing12))

                Text(
                    text = "Price: $$playerPrice",
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }

            IconButton(
                onClick = onDismiss,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(Dimens.spacing24)
                    .size(Dimens.spacing20)
                    .background(Color.Gray, CircleShape)
                    .size(Dimens.spacing20)
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