package com.example.composenews.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.composenews.ui.theme.ComposeNewsTheme
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