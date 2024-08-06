package com.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.project.presentation.HomeScreen
import com.example.project.presentation.HomeScreenViewModel
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
