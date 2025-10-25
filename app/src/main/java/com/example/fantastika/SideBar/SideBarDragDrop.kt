@file:OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)

package com.example.fantastika

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fantastika.DropZone.DropZone
import com.example.fantastika.SideBar.SideBarViewModel
import com.example.fantastika.SideBar.SidebarContent
import com.example.fantastika.SideBar.ThemeSwitcher
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SideBarDragDrop(
    viewModel: SideBarViewModel = viewModel(),
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val droppedZones by viewModel.droppedZones.collectAsState()
    val usedItems by viewModel.usedItems.collectAsState()
    var rotationAngle by remember { mutableStateOf(0f) }
    //var darkTheme by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        androidx.compose.animation.core.animate(
            initialValue = 0f,
            targetValue = 35f,
            animationSpec = androidx.compose.animation.core.tween(1500)
        ) { value, _ ->
            rotationAngle = value
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(Modifier.width(350.dp)) {
                SidebarContent(
                    usedItems = usedItems,
                    onItemDragStart = {
                        scope.launch { drawerState.close() }
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Fantastika") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    actions = {
                        ThemeSwitcher(
                            darkTheme = darkTheme,
                            size = 30.dp,
                            onClick = onThemeUpdated
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                )
            }
        ) { padding ->
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
                                ){
                                    Row (
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        horizontalArrangement = Arrangement.spacedBy(15.dp)
                                    ){
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
    }
}