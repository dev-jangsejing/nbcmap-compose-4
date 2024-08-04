package com.jess.nbcamp.compose4.user.signup

data class SignUpUiState(
    val name: String,
    val id: String,
    val password: String,
) {
    companion object {

        fun empty(
            name: String = "",
            id: String = "",
            password: String = "",
        ) = SignUpUiState(
            name = name,
            id = id,
            password = password,
        )

    }
}