package com.jess.nbcamp.compose4.market.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.jess.nbcamp.compose4.market.model.MarketSaleItem
import com.jess.nbcamp.compose4.ui.theme.NbCampTheme

class MarketDetailActivity : ComponentActivity() {

    companion object {

        const val EXTRA_ITEM = "extra_item"

        fun newIntent(
            context: Context,
            item: MarketSaleItem,
        ): Intent {
            return Intent(context, MarketDetailActivity::class.java).apply {
                putExtra(EXTRA_ITEM, item)
            }
        }
    }

    private val viewModel by viewModels<MarketDetailViewModel> {
        MarketDetailSavedStateViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NbCampTheme {
                MarketDetailScreen(
                    viewModel = viewModel,
                    onBackPressed = {
                        finish()
                    }
                )
            }
        }
    }
}