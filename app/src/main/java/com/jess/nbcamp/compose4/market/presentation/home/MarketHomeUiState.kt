package com.jess.nbcamp.compose4.market.presentation.home

import com.jess.nbcamp.compose4.market.model.MarketSaleItem

data class MarketHomeUiState(
    val title: String?,
    val items: List<MarketSaleItem>
) {
    companion object {

        fun empty(
            title: String? = null,
            items: List<MarketSaleItem> = emptyList(),
        ) = MarketHomeUiState(
            title = title,
            items = items,
        )
    }
}