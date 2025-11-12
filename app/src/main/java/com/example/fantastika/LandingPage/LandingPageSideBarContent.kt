package com.example.fantastika.LandingPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.fantastika.Common.Dimens
import com.example.fantastika.R

@Composable
fun LandingPageSideBarContent(
    modifier: Modifier = Modifier,
    onHomeClick: () -> Unit = {},
    onLeaderboardClick: () -> Unit = {},
    onStatisticsClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFC68642))
            .padding(Dimens.spacing20)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.sidebarlogo),
                    contentDescription = "Logo",
                )
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = Dimens.spacing8))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onHomeClick)
                    .padding(vertical = Dimens.spacing8),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    modifier = Modifier.size(Dimens.spacing24),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(Dimens.spacing16))
                Text(
                    text = "Home",
                    color = Color.White
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onLeaderboardClick)
                    .padding(vertical = Dimens.spacing8),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Leaderboard,
                    contentDescription = "Leaderboard",
                    modifier = Modifier.size(Dimens.spacing24),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(Dimens.spacing16))
                Text(
                    text = "Leaderboard",
                    color = Color.White
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onStatisticsClick)
                    .padding(vertical = Dimens.spacing8),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.QueryStats,
                    contentDescription = "Statistics",
                    modifier = Modifier.size(Dimens.spacing24),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(Dimens.spacing16))
                Text(
                    text = "Statistics",
                    color = Color.White
                )
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .clickable(onClick = onLogoutClick)
                .padding(vertical = Dimens.spacing8),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Log out",
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LandingPageSideContentPrev() {
    LandingPageSideBarContent()
}