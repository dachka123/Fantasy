package com.example.fantastika.LandingPage

import androidx.compose.runtime.Composable
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
    onThemeUpdated: () -> Unit,
    onNavigateToLogin: () -> Unit,
) {

    SideBarNav(
        //title = "Landing",
        topBarContent = { LandingPageTopBarContent(
            onNavigateToLogin = onNavigateToLogin
        ) },

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