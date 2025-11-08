package com.example.fantastika.Common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fantastika.ui.theme.FantastikaTheme

@Composable
fun StyledBox(
    modifier: Modifier = Modifier,
    backgroundColor: Color = FantastikaTheme.color.background,
    borderColor: Color = FantastikaTheme.color.onBackground,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .border(Dimens.spacing1, borderColor, RoundedCornerShape(Dimens.spacing24))
            .background(backgroundColor, RoundedCornerShape(Dimens.spacing24))
            .padding(horizontal = Dimens.spacing14, vertical = Dimens.spacing8),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}
