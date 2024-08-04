package com.jess.nbcamp.compose4.state

data class StatelessUiState(
    val name: String,
) {
    companion object {

        fun empty(
            name: String = ""
        ) = StatelessUiState(
            name = name,
        )

    }
}