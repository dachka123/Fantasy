package com.example.fantastika.PlayerSelection.Presentation.PlayerSelectionSideBar

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.fantastika.Common.Dimens
import com.example.fantastika.ui.theme.FantastikaTheme

@Composable
fun ThemeSwitcher(
    darkTheme: Boolean = false,
    size: Dp = Dimens.spacing150,
    iconSize: Dp = size / 3,
    padding: Dp = Dimens.spacing10,
    borderWidth: Dp = Dimens.spacing1,
    parentShape: Shape = CircleShape,
    toggleShape: Shape = CircleShape,
    animationSpec: AnimationSpec<Dp> = tween(durationMillis = 300),
    onClick: () -> Unit
) {
    val offset by animateDpAsState(
        targetValue = if (darkTheme) 0.dp else size,
        animationSpec = animationSpec,
        label = "theme_switcher_offset"
    )

    Box(modifier = Modifier
        .width(size * 2)
        .height(size)
        .clip(shape = parentShape)
        .clickable { onClick() }
        .background(FantastikaTheme.color.secondary)
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .offset(x = offset)
                .padding(all = padding)
                .clip(shape = toggleShape)
                .background(FantastikaTheme.color.primary)
        ) {}
        Row(
            modifier = Modifier
                .border(
                    border = BorderStroke(
                        width = borderWidth,
                        color = FantastikaTheme.color.primary
                    ),
                    shape = parentShape
                )
        ) {
            Box(
                modifier = Modifier.size(size),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(iconSize),
                    imageVector = Icons.Default.Nightlight,
                    contentDescription = "Theme Icon",
                    tint = if (darkTheme) FantastikaTheme.color.secondary
                    else FantastikaTheme.color.primary
                )
            }
            Box(
                modifier = Modifier.size(size),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(iconSize),
                    imageVector = Icons.Default.LightMode,
                    contentDescription = "Theme Icon",
                    tint = if (darkTheme) FantastikaTheme.color.primary
                    else FantastikaTheme.color.secondary
                )
            }
        }
    }
}