package com.example.fantastika.LandingPage

import androidx.compose.runtime.Composable
import com.example.fantastika.LandingPage.Components.FixtureBoxes
import com.example.fantastika.Common.SideBarNav

@Composable
fun MainLandingPage(
    onStartApp: () -> Unit,
    onNavigateToFixtures: () -> Unit,
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit
) {

    SideBarNav(
        title = "Landing",
        darkTheme = darkTheme,
        onThemeUpdated = onThemeUpdated,

        drawerContent = { closeDrawer ->
            LandingPageSideBarContent()
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