package com.example.fantastika.PlayerSelection.DropZone

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.fantastika.Common.Dimens
import com.example.fantastika.R
import com.example.fantastika.ui.theme.FantastikaTheme

@Composable
fun PlayerCard(
    playerName: String,
    price: Int,
    rating: Int = 2
) {
    Card(
        shape = RoundedCornerShape(Dimens.spacing16),
        colors = CardDefaults.cardColors(containerColor = FantastikaTheme.color.secondary)
    ) {
        Box(Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.imagelogo),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = Dimens.spacing30, top = Dimens.spacing10, end = Dimens.spacing8, bottom = Dimens.spacing30)
                    .align(Alignment.TopCenter)
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
                        text = playerName,
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
                        color = FantastikaTheme.color.onSecondary
                    )
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = Dimens.spacing10)
                    .height(Dimens.spacing28)
                    .clip(TrapeziumShape(cutAmount = 40f))
                    .background(FantastikaTheme.color.background)
                    .padding(horizontal = Dimens.spacing10),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$$price",
                    color = FantastikaTheme.color.onBackground,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(Dimens.spacing35)
                    .clip(RoundedCornerShape(topStart = Dimens.spacing8))
                    .background(FantastikaTheme.color.secondary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = rating.toString(),
                    color = FantastikaTheme.color.onSecondary,
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

@Preview
@Composable
private fun PlayerCardPreview() {
    PlayerCard(
        playerName = "Player Name",
        price = 1150,
        rating = 2
    )
}
