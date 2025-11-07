@file:OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)

package com.example.fantastika

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fantastika.PlayerSelection.DropZone.DropZone
import com.example.fantastika.PlayerSelection.PlayerSelectionSideBar.SideBarViewModel
import com.example.fantastika.PlayerSelection.PlayerSelectionSideBar.SidebarContent
import com.example.fantastika.PlayerSelection.Data.allPlayers
import com.example.fantastika.Common.SideBarNav
import com.example.fantastika.LandingPage.Components.TopBarContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SideBarDragDrop(
    viewModel: SideBarViewModel = viewModel(),
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit,
    onBackPressed: () -> Unit
) {
    val droppedZones by viewModel.droppedZones.collectAsState()
    val usedItems by viewModel.usedItems.collectAsState()
    var rotationAngle by remember { mutableStateOf(0f) }

    BackHandler {
        onBackPressed()
    }

    LaunchedEffect(Unit) {
        animate(
            initialValue = 0f,
            targetValue = 35f,
            animationSpec = tween(1500)
        ) { value, _ ->
            rotationAngle = value
        }
    }

    SideBarNav(
        //title = "Fantastika",
        topBarContent = { TopBarContent() },
        darkTheme = darkTheme,
        onThemeUpdated = onThemeUpdated,
        onBackPressed = onBackPressed,

        drawerContent = { closeDrawer ->
            SidebarContent(
                allPlayers = allPlayers,
                usedItems = usedItems,
                onItemDragStart = {
                    closeDrawer()
                }
            )
        },

        screenContent = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.TopCenter
            ) {
                Image(
                    painter = painterResource(id = R.drawable.background4),
                    contentDescription = "App Background",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(490.dp)
                            .padding(horizontal = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.court),
                            contentDescription = "DropZone Background",
                            modifier = Modifier
                                .fillMaxSize()
                                .graphicsLayer {
                                    rotationX = rotationAngle
                                    cameraDistance = 8 * density
                                },
                            contentScale = ContentScale.Crop
                        )

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(64.dp),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 100.dp)
                        ) {
                            DropZone(
                                droppedItem = droppedZones[0],
                                onItemDropped = { viewModel.onItemDropped(0, it) },
                                onItemRemoved = { viewModel.onItemRemoved(0, it) },
                                usedItems = usedItems
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                DropZone(
                                    droppedItem = droppedZones[1],
                                    onItemDropped = { viewModel.onItemDropped(1, it) },
                                    onItemRemoved = { viewModel.onItemRemoved(1, it) },
                                    usedItems = usedItems
                                )
                                DropZone(
                                    droppedItem = droppedZones[2],
                                    onItemDropped = { viewModel.onItemDropped(2, it) },
                                    onItemRemoved = { viewModel.onItemRemoved(2, it) },
                                    usedItems = usedItems
                                )
                            }
                        }
                    }

                    //Active players
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 40.dp)
                            .horizontalScroll(rememberScrollState()),
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize(),
                                horizontalArrangement = Arrangement.spacedBy(15.dp)
                            ) {
                                DropZone(
                                    droppedItem = droppedZones[0],
                                    onItemDropped = { viewModel.onItemDropped(0, it) },
                                    onItemRemoved = { viewModel.onItemRemoved(0, it) },
                                    usedItems = usedItems
                                )
                                DropZone(
                                    droppedItem = droppedZones[1],
                                    onItemDropped = { viewModel.onItemDropped(1, it) },
                                    onItemRemoved = { viewModel.onItemRemoved(1, it) },
                                    usedItems = usedItems
                                )
                                DropZone(
                                    droppedItem = droppedZones[2],
                                    onItemDropped = { viewModel.onItemDropped(2, it) },
                                    onItemRemoved = { viewModel.onItemRemoved(2, it) },
                                    usedItems = usedItems
                                )

                                //bench
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(start = 45.dp),
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        horizontalArrangement = Arrangement.spacedBy(15.dp)
                                    ) {
                                        DropZone(
                                            droppedItem = droppedZones[3],
                                            onItemDropped = { viewModel.onItemDropped(3, it) },
                                            onItemRemoved = { viewModel.onItemRemoved(3, it) },
                                            usedItems = usedItems
                                        )
                                        DropZone(
                                            droppedItem = droppedZones[4],
                                            onItemDropped = { viewModel.onItemDropped(4, it) },
                                            onItemRemoved = { viewModel.onItemRemoved(4, it) },
                                            usedItems = usedItems
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}