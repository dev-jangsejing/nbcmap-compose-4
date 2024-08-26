package com.jess.nbcamp.compose4.market.presentation.detail

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jess.nbcamp.compose4.market.model.MarketSaleItem
import com.jess.nbcamp.compose4.market.presentation.detail.MarketDetailActivity.Companion.EXTRA_ITEM
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MarketDetailViewModel(
    private val stateHandle: SavedStateHandle,
) : ViewModel() {

    private val item get() = stateHandle.get<MarketSaleItem>(EXTRA_ITEM)

    private val _uiState = MutableStateFlow(MarketDetailUiState.empty())
    val uiState: StateFlow<MarketDetailUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { prev ->
                prev.copy(
                    title = item?.itemTitle,
                    item = item,
                )
            }
        }
    }

}

class MarketDetailSavedStateViewModelFactory : AbstractSavedStateViewModelFactory() {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return MarketDetailViewModel(
            handle
        ) as T
    }
}


