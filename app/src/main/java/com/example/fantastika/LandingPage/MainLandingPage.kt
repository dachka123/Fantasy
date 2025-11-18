package com.example.fantastika.LandingPage

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.fantastika.Common.Dimens
import com.example.fantastika.LandingPage.Components.FixtureBoxes
import com.example.fantastika.Common.SideBarNav
import com.example.fantastika.LandingPage.Components.LandingPageTopBarContent
import com.example.fantastika.PlayerSelection.Presentation.PlayerSelectionSideBar.ThemeSwitcher

@Composable
fun MainLandingPage(
    onStartApp: () -> Unit,
    onNavigateToFixtures: () -> Unit,
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit,
    onNavigateToLogin: () -> Unit,
    isUserLoggedIn: Boolean,
    loggedInUsername: String?,
    onLogout: () -> Unit,
) {
    val context = LocalContext.current

    SideBarNav(
        //title = "Landing",
        topBarContent = { LandingPageTopBarContent(
            onNavigateToLogin = onNavigateToLogin,
            loggedInUsername = loggedInUsername,
        ) },

        drawerContent = { closeDrawer ->
            LandingPageSideBarContent(
                isUserLoggedIn = isUserLoggedIn,
                onLogoutClick = {
                    closeDrawer()
                    onLogout()
                }
            )
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
                    if (isUserLoggedIn) {
                        onNavigateToFixtures()
                    } else {
                        Toast.makeText(
                            context,
                            "First login if you want to select players or see a team",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            )
        }
    )
}