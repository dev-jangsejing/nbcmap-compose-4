package com.jess.nbcamp.compose4.market.presentation.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.jess.nbcamp.compose4.ui.theme.NbCampTheme

class MarketHomeActivity : ComponentActivity() {

    private val viewModel: MarketHomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NbCampTheme {
                MarketHomeScreen(
                    viewModel = viewModel,
                )
            }
        }
    }
}