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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fantastika.R

@Composable
fun PlayerCard(
    playerName: String,
    price: Int
) {

    val infiniteTransition = rememberInfiniteTransition()
    val viewportWidth = 40.dp
    val contentWidth = 200.dp
    val targetMarqueeOffset = -(contentWidth.value)

    val offsetX by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = targetMarqueeOffset,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 6000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Black),
    ) {
        Box(Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.imagelogo),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 40.dp,
                        top = 4.dp,
                        end = 8.dp,
                        bottom = 30.dp
                    )
                    .align(Alignment.TopCenter)
            )
            Box(
                modifier = Modifier
                    .width(viewportWidth) // Fixed width of the visible name area
                    .fillMaxHeight()
                    .background(Color.Black)
                    // FIX: Position to the start of the card
                    .align(Alignment.CenterStart)
                    // Clip the corners and the content
                    .clip(RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .width(contentWidth)
                        .rotate(90f)
                        // FIX: Apply the scrolling offset to the content
                        .offset(x = offsetX.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = playerName,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        softWrap = false,
                    )
                }
            }

            //.width(maxMarqueeWidth)
            //                    .fillMaxHeight()
            //                    .background(Color.Black)
            //
            //                    .rotate(90f) // Apply rotation here
            //                    .offset(x = offsetX.dp)
            //                    //.align(Alignment.CenterStart),
            //                    .clip(RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)),
            //                contentAlignment = Alignment.Center


            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 10.dp)
                    .height(28.dp)
                    .clip((TrapeziumShape(cutAmount = 40f)))
                    .background(Color.White)
                    .padding(horizontal = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$$price",
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
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
                    text = "2",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
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
        price = 1150
    )
}