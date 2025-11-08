package com.example.fantastika.LandingPage.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import com.example.fantastika.Common.Dimens
import com.example.fantastika.R
import com.example.fantastika.ui.theme.FantastikaTheme

@Composable
fun FixtureBoxes(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = Dimens.spacing130)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.spacing20)
                .height(Dimens.spacing300)
                .background(
                    FantastikaTheme.color.primary,
                    shape = RoundedCornerShape(Dimens.spacing12)
                )
                .clickable(onClick = onClick)
                .padding(Dimens.spacing32)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = Dimens.spacing16)
                ) {
                    Text(
                        text = "KICK OFF",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = FantastikaTheme.color.background
                        )
                    )

                    Spacer(modifier = Modifier.height(Dimens.spacing8))

                    Text(
                        modifier = Modifier
                            .padding(end = Dimens.spacing10),
                        text = "Play a quick match against any team!",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = FantastikaTheme.color.background.copy(alpha = 0.8f)
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Card(
                    shape = RoundedCornerShape(Dimens.spacing20),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White.copy(alpha = 0.15f)
                    ),
                    modifier = Modifier
                        .size(width = Dimens.spacing120, height = Dimens.spacing160)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Player Icon",
                            tint = FantastikaTheme.color.background,
                            modifier = Modifier.size(Dimens.spacing60)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(Dimens.spacing16))

        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.spacing20)
                .height(Dimens.spacing300)
                .background(
                    FantastikaTheme.color.onTertiary,
                    shape = RoundedCornerShape(Dimens.spacing12)
                )
                .padding(Dimens.spacing32)
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ) {
                Text(
                    text = "Fixtures",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = FantastikaTheme.color.background,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Left Team
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        shape = RoundedCornerShape(Dimens.spacing100),
                        modifier = Modifier.size(Dimens.spacing80)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.imagelogo),
                            contentDescription = "Player Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(topStart = Dimens.spacing24, bottomStart = Dimens.spacing24))
                        )
                    }
                    Spacer(modifier = Modifier.height(Dimens.spacing8))
                    Text(
                        text = "Thunder Hawks",
                        color = FantastikaTheme.color.background,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "VS",
                        color = FantastikaTheme.color.background,
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Black)
                    )
                    Spacer(modifier = Modifier.height(Dimens.spacing5))
                    Text(
                        text = "NEXT MATCH",
                        color = FantastikaTheme.color.background.copy(alpha = 0.7f),
                        style = MaterialTheme.typography.bodySmall
                    )
                }


                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        shape = RoundedCornerShape(Dimens.spacing100),
                        modifier = Modifier.size(Dimens.spacing80)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.imagelogo),
                            contentDescription = "Player Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(topStart = Dimens.spacing24, bottomStart = Dimens.spacing24))
                        )
                    }
                    Spacer(modifier = Modifier.height(Dimens.spacing8))
                    Text(
                        text = "Thunder Hawks",
                        color = FantastikaTheme.color.background,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun FixtureBoxesPrev() {
    FixtureBoxes()
}