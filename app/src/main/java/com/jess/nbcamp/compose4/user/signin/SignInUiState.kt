package com.jess.nbcamp.compose4.user.signin

data class SignInUiState(
    val id: String,
    val password: String,
) {

    companion object {

        fun empty(
            id: String = "",
            password: String = "",
        ) = SignInUiState(
            id = id,
            password = password,
        )
    }
}