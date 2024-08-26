package com.jess.nbcamp.compose4.market.presentation.detail

import com.jess.nbcamp.compose4.market.model.MarketSaleItem

data class MarketDetailUiState(
    val title: String?,
    val item: MarketSaleItem?,
) {
    companion object {

        fun empty(
            title: String? = null,
            item: MarketSaleItem? = null
        ) = MarketDetailUiState(
            title = title,
            item = item,
        )
    }
}