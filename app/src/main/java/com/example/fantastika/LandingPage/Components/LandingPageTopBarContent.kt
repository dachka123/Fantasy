package com.example.fantastika.LandingPage.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.fantastika.Common.Dimens
import com.example.fantastika.ui.theme.FantastikaTheme

@Composable
fun LandingPageTopBarContent(
    modifier: Modifier = Modifier,
    onNavigateToLogin: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Box(
            modifier = Modifier
                .padding(end = Dimens.spacing10)
                .clip(RoundedCornerShape(Dimens.spacing24))
                .background(FantastikaTheme.color.background)
                .border(
                    width = Dimens.spacing1,
                    color = FantastikaTheme.color.onBackground,
                    shape = RoundedCornerShape(Dimens.spacing24)
                )
                .clickable { onNavigateToLogin() }
                .padding(horizontal = Dimens.spacing8, vertical = Dimens.spacing2),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Log in",
                color = FantastikaTheme.color.onBackground
            )
        }
    }
}