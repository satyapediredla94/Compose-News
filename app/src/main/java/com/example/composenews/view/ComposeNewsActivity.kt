package com.example.composenews.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composenews.ui.theme.ComposeNewsTheme
import com.example.composenews.view.navigation.BottomNavigationBar
import com.example.composenews.view.navigation.NavigationalTopBar
import com.example.composenews.view.navigation.NewsNavigationGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComposeNewsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeNewsTheme {
                ComposeNewsApp()
            }
        }
    }
}