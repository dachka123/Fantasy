package com.example.fantastika.SideBar.DropZone

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.fantastika.R

@Composable
fun PlayerCard(
    playerName: String,
    price: Int,
    rating: Int = 2
) {
    val viewportWidth = 40.dp
    val contentHeight = 120.dp
    val targetOffsetY = -80f

    // Animate player name from bottom to top
    val infiniteTransition = rememberInfiniteTransition(label = "nameAnimation")
    val offsetY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = targetOffsetY,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "offsetY"
    )

    Card(
        modifier = Modifier.size(200.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Black)
    ) {
        Box(Modifier.fillMaxSize()) {
            // Background image
            Image(
                painter = painterResource(id = R.drawable.imagelogo),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 30.dp, top = 4.dp, end = 8.dp, bottom = 30.dp)
                    .align(Alignment.TopCenter)
            )

            // Vertical red bar with animated name
            Box(
                modifier = Modifier
                    .width(viewportWidth)
                    .fillMaxHeight()
                    .background(Color.Black)
                    .align(Alignment.CenterStart)
                    .clip(RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)),
                contentAlignment = Alignment.Center
            ) {
                // Give enough horizontal room after rotation
                Box(
                    modifier = Modifier
                        .background(Color.Red)
                        .width(200.dp) // 👈 widen enough for long names
                        .offset(y = offsetY.dp)
                        .rotate(90f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = playerName,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        softWrap = false,          // prevent weird wrapping
                        maxLines = 1,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }

    }


            // Price label
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

            // Rating box
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
