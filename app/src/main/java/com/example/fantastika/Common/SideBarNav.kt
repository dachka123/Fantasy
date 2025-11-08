package com.example.fantastika.Common

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fantastika.PlayerSelection.PlayerSelectionSideBar.ThemeSwitcher
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SideBarNav(
    //title: String,
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit,
    onBackPressed: (() -> Unit)? = null,
    drawerContent: @Composable (closeDrawer: () -> Unit) -> Unit,
    screenContent: @Composable (PaddingValues) -> Unit,
    topBarContent: @Composable () -> Unit,
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    val closeDrawer: () -> Unit = { scope.launch { drawerState.close() } }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(Modifier.width(Dimens.spacing350)) {
                drawerContent(closeDrawer)
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.horizontalScroll(scrollState)
                        ) {
                            if (onBackPressed != null) {
                                IconButton(onClick = onBackPressed) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                }
                                Spacer(modifier = Modifier.width(Dimens.spacing8))
                            }

                            //Text(title)
                            topBarContent()
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    actions = {
                        ThemeSwitcher(
                            darkTheme = darkTheme,
                            size = Dimens.spacing30,
                            onClick = onThemeUpdated
                        )
                        Spacer(modifier = Modifier.width(Dimens.spacing8))
                    }
                )
            }
        ) { padding ->
            screenContent(padding)
        }
    }
}