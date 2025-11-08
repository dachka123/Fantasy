package com.example.fantastika.LandingPage.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fantastika.Common.Dimens
import com.example.fantastika.Common.StyledBox
import com.example.fantastika.ui.theme.FantastikaTheme

@Composable
fun TopBarContent(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Spacer(modifier = Modifier.width(Dimens.spacing35))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Dimens.spacing12)
        ) {
            StyledBox(
                backgroundColor = FantastikaTheme.color.orange,
                borderColor = FantastikaTheme.color.orange,
                modifier = Modifier
                    .heightIn(min = 26.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(Dimens.spacing5)
                ) {
                    Icon(
                        imageVector = Icons.Filled.MonetizationOn,
                        contentDescription = "Coin",
                        tint = Color(0xFFFFC107),
                        modifier = Modifier.size(Dimens.spacing20)
                    )
                    Text(
                        text = "326K",
                        color = FantastikaTheme.color.background
                    )
                }
            }

            StyledBox(
                backgroundColor = FantastikaTheme.color.background,
                borderColor = FantastikaTheme.color.onBackground,
                modifier = Modifier
                    .heightIn(min = 26.dp)
            ) {
                Text(
                    text = "Sign in",
                    color = FantastikaTheme.color.onBackground
                )
            }
        }
    }
}

@Preview
@Composable
private fun TopBarContentPrev() {
    TopBarContent()
}