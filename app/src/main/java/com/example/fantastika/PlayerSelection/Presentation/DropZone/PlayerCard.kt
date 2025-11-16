package com.example.fantastika.PlayerSelection.Presentation.DropZone

import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import coil.compose.AsyncImage
import com.example.fantastika.Common.Dimens
import com.example.fantastika.PlayerSelection.Domain.SimplePlayer

@Composable
fun PlayerCard(
    player: SimplePlayer,
    rating: Int = 2
) {
    Card(
        shape = RoundedCornerShape(Dimens.spacing16),
        colors = CardDefaults.cardColors(containerColor = Color.Black)
    ) {
        Box(Modifier.fillMaxSize()) {
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
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset(x = (-32).dp, y = (-14).dp)
                    .rotate(-360f)
                    .width(Dimens.spacing150)
                    .height(Dimens.spacing30),
                contentAlignment = Alignment.Center
            ) {
                Row(modifier = Modifier.padding(Dimens.spacing5)) {
                    Text(
                        text = player.name,
                        modifier = Modifier
                            .padding(horizontal = Dimens.spacing2)
                            .graphicsLayer {
                                rotationZ = 90f
                            }
                            .basicMarquee(
                                iterations = Int.MAX_VALUE,
                                animationMode = MarqueeAnimationMode.Immediately,
                                spacing = MarqueeSpacing.fractionOfContainer(1f / 3f),
                                initialDelayMillis = 0,
                                velocity = 50.dp
                            ),
                        color = Color.White
                    )
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = Dimens.spacing10)
                    .height(Dimens.spacing28)
                    .clip(TrapeziumShape(cutAmount = 40f))
                    .background(Color.White)
                    .padding(horizontal = Dimens.spacing10),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$${String.format("%.2f", player.price)}",
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(Dimens.spacing35)
                    .clip(RoundedCornerShape(topStart = Dimens.spacing8))
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = rating.toString(),
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

class TrapeziumShape(private val cutAmount: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(size.width - cutAmount, 0f)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
        return Outline.Generic(path)
    }
}

/*@Preview
@Composable
private fun PlayerCardPreview() {
    PlayerCard(
        playerName = "Player Name",
        price = 1150,
        rating = 2
    )
}*/
