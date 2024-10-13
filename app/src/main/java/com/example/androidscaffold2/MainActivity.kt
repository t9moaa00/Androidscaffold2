package com.example.androidscaffold2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidscaffold2.ui.theme.Androidscaffold2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Androidscaffold2Theme {
                ScaffoldApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { MainScreen(navController) }
        composable("info") { InfoScreen(navController) }
        composable("settings") { SettingsScreen(navController) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = { MainTopBar(title = "My App", navController = navController) },
        content = { paddingValues ->
            Text(
                text = "Content for Home screen",
                modifier = Modifier.padding(paddingValues)
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(navController: NavHostController) {
    Scaffold(
        topBar = { ScreenTopBar(title = "Info", navController = navController) },
        content = { paddingValues ->
            Text(
                text = "Content for Info screen",
                modifier = Modifier.padding(paddingValues)
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavHostController) {
    Scaffold(
        topBar = { ScreenTopBar(title = "Settings", navController = navController) },
        content = { paddingValues ->
            Text(
                text = "Content for Settings screen",
                modifier = Modifier.padding(paddingValues)
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String, navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(text = title) },
        actions = {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Filled.MoreVert, contentDescription = null)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        navController.navigate("info")
                    },
                    // Correctly passing the Text as the parameter
                    text = { Text("Info") }
                )
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        navController.navigate("settings")
                    },
                    // Correctly passing the Text as the parameter
                    text = { Text("Settings") }
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(title: String, navController: NavHostController) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewScaffoldApp() {
    Androidscaffold2Theme {
        ScaffoldApp()
    }
}
