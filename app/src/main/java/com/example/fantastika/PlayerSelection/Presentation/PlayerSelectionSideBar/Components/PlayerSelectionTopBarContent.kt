package com.example.fantastika.PlayerSelection.Presentation.PlayerSelectionSideBar.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fantastika.Common.Dimens
import com.example.fantastika.ui.theme.FantastikaTheme
import java.text.DecimalFormat

@Composable
fun PlayerSelectionTopBarContent(
    remainingBudget: Double,
    modifier: Modifier = Modifier
) {
    val formatBudget = remember(remainingBudget) {
        val df = DecimalFormat("#,##0.0")
        val budgetInK = remainingBudget / 10
        "${df.format(budgetInK)}K"
    }

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
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(Dimens.spacing24))
                    .background(FantastikaTheme.color.orange)
                    .border(
                        width = Dimens.spacing1,
                        color = FantastikaTheme.color.orange,
                        shape = RoundedCornerShape(Dimens.spacing24)
                    )
                    .padding(horizontal = Dimens.spacing8, vertical = Dimens.spacing2),
                contentAlignment = Alignment.Center
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
                        text = formatBudget,
                        color = FantastikaTheme.color.background
                    )
                }
            }
        }
    }
}