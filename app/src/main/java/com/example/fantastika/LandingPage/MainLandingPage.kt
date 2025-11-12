package com.example.fantastika.LandingPage

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.fantastika.Common.Dimens
import com.example.fantastika.LandingPage.Components.FixtureBoxes
import com.example.fantastika.Common.SideBarNav
import com.example.fantastika.LandingPage.Components.LandingPageTopBarContent
import com.example.fantastika.PlayerSelection.PlayerSelectionSideBar.ThemeSwitcher

@Composable
fun MainLandingPage(
    onStartApp: () -> Unit,
    onNavigateToFixtures: () -> Unit,
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit
) {

    SideBarNav(
        //title = "Landing",
        topBarContent = { LandingPageTopBarContent(Modifier.fillMaxWidth()) },

        drawerContent = { closeDrawer ->
            LandingPageSideBarContent()
        },

        themeSwitcherContent = {
            ThemeSwitcher(
                darkTheme = darkTheme,
                size = Dimens.spacing30,
                onClick = onThemeUpdated
            )
        },

        screenContent = { padding ->
            FixtureBoxes(
                onClick = {
                    onNavigateToFixtures()
                }
            )
        }
    )
}