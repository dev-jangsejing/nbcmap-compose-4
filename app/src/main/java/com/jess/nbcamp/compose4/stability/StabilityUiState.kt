package com.jess.nbcamp.compose4.stability

import androidx.compose.runtime.Stable

@Stable
data class StabilityUiState(
    val isLoading: Boolean,
    val contacts: List<String>,
)