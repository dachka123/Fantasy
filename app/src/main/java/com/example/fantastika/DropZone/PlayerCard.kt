package com.example.fantastika.DropZone

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
import com.example.fantastika.R

@Composable
fun PlayerCard(
    playerName: String,
    price: Int,
    rating: Int = 2
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Black)
    ) {
        Box(Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.imagelogo),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 30.dp, top = 10.dp, end = 8.dp, bottom = 30.dp)
                    .align(Alignment.TopCenter)
            )
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset(x = (-32).dp, y = (-14).dp)
                    .rotate(-360f)
                    .width(150.dp)
                    .height(30.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(modifier = Modifier.padding(5.dp)) {
                    Text(
                        text = playerName,
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
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
                    .padding(bottom = 10.dp)
                    .height(28.dp)
                    .clip(TrapeziumShape(cutAmount = 40f))
                    .background(Color.White)
                    .padding(horizontal = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$$price",
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(35.dp)
                    .clip(RoundedCornerShape(topStart = 8.dp))
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

@Preview
@Composable
private fun PlayerCardPreview() {
    PlayerCard(
        playerName = "Player Name",
        price = 1150,
        rating = 2
    )
}
