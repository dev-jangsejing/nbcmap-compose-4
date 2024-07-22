package com.jess.nbcamp.compose4.mvvm

data class MvvmUiState(
    val header: String?,
    val title: String?,
    val content: String?,
    val user: String?,
    val viewCount: Int,
) {

    companion object {

        fun empty(
            header: String? = null,
            title: String? = null,
            content: String? = null,
            user: String? = null,
            viewCount: Int = 0,
        ) = MvvmUiState(
            header = header,
            title = title,
            content = content,
            user = user,
            viewCount = viewCount,
        )
    }
}