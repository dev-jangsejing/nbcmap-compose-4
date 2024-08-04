package com.jess.nbcamp.compose4.state

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class StatelessViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(StatelessUiState.empty())
    val uiState: StateFlow<StatelessUiState> = _uiState.asStateFlow()

    fun onValueChange(name: String) {
        _uiState.update { prev ->
            prev.copy(name = name)
        }
    }
}