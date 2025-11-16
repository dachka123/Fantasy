package com.example.fantastika.PlayerSelection.Presentation.Common

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.fantastika.Common.Dimens


@Composable
fun FilterSection(
    filterMode: FilterMode,
    selectedTeam: String?,
    onFilterChange: (FilterMode) -> Unit,
    onBackToTeams: () -> Unit,
    segmentBackgroundColor: Color = Color(0xFFB9B9BE)
) {
    if (filterMode == FilterMode.TEAM_PLAYERS) {
        // Back Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Dimens.spacing12),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackToTeams) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back to Teams",
                    tint = Color.Black
                )
            }
            Text(
                text = selectedTeam ?: "Back to Teams",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = Dimens.spacing8),
                color = Color.Black
            )
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Dimens.spacing12, start = Dimens.spacing16, end = Dimens.spacing16)
                .height(Dimens.spacing40)
                .clip(RoundedCornerShape(Dimens.spacing100))
                .background(segmentBackgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.spacing5),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Players Segment
                val isPlayersSelected = filterMode == FilterMode.PLAYERS
                val playersBackgroundColor by animateColorAsState(
                    targetValue = if (isPlayersSelected) Color.White else Color.Transparent,
                    animationSpec = spring(dampingRatio = Spring.DampingRatioNoBouncy),
                    label = "playersBackground"
                )
                val playersTextColor by animateColorAsState(
                    targetValue = if (isPlayersSelected) Color.Black else Color.White,
                    animationSpec = tween(durationMillis = 300),
                    label = "playersText"
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(Dimens.spacing32)
                        .clip(RoundedCornerShape(Dimens.spacing100))
                        .background(playersBackgroundColor)
                        .clickable { onFilterChange(FilterMode.PLAYERS) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Players",
                        style = TextStyle(fontSize = 14.sp),
                        color = playersTextColor,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                // Teams Segment
                val isTeamsSelected = filterMode == FilterMode.TEAMS
                val teamsBackgroundColor by animateColorAsState(
                    targetValue = if (isTeamsSelected) Color.White else Color.Transparent,
                    animationSpec = spring(dampingRatio = Spring.DampingRatioNoBouncy),
                    label = "teamsBackground"
                )
                val teamsTextColor by animateColorAsState(
                    targetValue = if (isTeamsSelected) Color.Black else Color.White,
                    animationSpec = tween(durationMillis = 300),
                    label = "teamsText"
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(Dimens.spacing32)
                        .clip(RoundedCornerShape(Dimens.spacing100))
                        .background(teamsBackgroundColor)
                        .clickable { onFilterChange(FilterMode.TEAMS) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Teams",
                        style = TextStyle(fontSize = 14.sp),
                        color = teamsTextColor,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}


