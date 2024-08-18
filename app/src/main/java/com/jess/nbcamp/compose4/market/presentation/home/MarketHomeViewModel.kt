package com.jess.nbcamp.compose4.market.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jess.nbcamp.compose4.market.model.MarketSaleItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MarketHomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MarketHomeUiState.empty())
    val uiState: StateFlow<MarketHomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { prev ->
                prev.copy(
                    items = MarketSaleItem.items
                )
            }
        }
    }

}