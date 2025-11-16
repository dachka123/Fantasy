package com.example.fantastika

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.example.fantastika.Navigation.AppNavHost
import com.example.fantastika.ui.theme.FantastikaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var darkTheme by remember { mutableStateOf(false) }
            val navController = rememberNavController()
            FantastikaTheme(darkTheme = darkTheme) {
                AppNavHost(
                    navController = navController,
                    darkTheme = darkTheme,
                    onThemeUpdated = { darkTheme = !darkTheme }
                )
            }
        }
    }
}