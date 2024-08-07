package com.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.project.presentation.HomeScreen
import com.example.project.presenatation.HomeScreen.HomeScreenViewModel
import com.example.project.ui.theme.ProjectTheme

class MainActivity : ComponentActivity() {

    private val homeScreenViewModel by viewModels<HomeScreenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectTheme {

                HomeScreen(viewModel = homeScreenViewModel)

            }
        }
    }
}
