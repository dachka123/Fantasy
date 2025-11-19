package com.example.fantastika.LandingPage
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.example.fantastika.Common.Dimens
import com.example.fantastika.Common.SideBarNav
import com.example.fantastika.LandingPage.Components.LandingPageTopBarContent
import com.example.fantastika.LandingPage.SideBar.LandingPageSideBarContent
import com.example.fantastika.PlayerSelection.Presentation.PlayerSelectionSideBar.ThemeSwitcher

@Composable
fun LandingScreenWrapper(
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit,
    isUserLoggedIn: Boolean,
    loggedInUsername: String?,
    onLogout: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToLeaderboard: () -> Unit,
    onNavigateToStatistics: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onNavigateToFixtures: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    SideBarNav(
        topBarContent = {
            LandingPageTopBarContent(
                onNavigateToLogin = onNavigateToLogin,
                loggedInUsername = loggedInUsername,
            )
        },

        drawerContent = { closeDrawer ->
            LandingPageSideBarContent(
                isUserLoggedIn = isUserLoggedIn,
                onHomeClick = {
                    closeDrawer()
                    onNavigateToHome()
                },
                onLeaderboardClick = {
                    closeDrawer()
                    onNavigateToLeaderboard()
                },
                onStatisticsClick = {
                    closeDrawer()
                    onNavigateToStatistics()
                },
                onLogoutClick = {
                    closeDrawer()
                    onLogout()
                },
                onNavigateToFixtures = {
                    closeDrawer()
                    onNavigateToFixtures()
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

        screenContent = content
    )
}