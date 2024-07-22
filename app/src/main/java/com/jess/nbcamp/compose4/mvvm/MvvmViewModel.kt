package com.jess.nbcamp.compose4.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MvvmViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MvvmUiState.empty())
    val uiState: StateFlow<MvvmUiState> = _uiState.asStateFlow()

    fun requestBoard() {
        // api 요청 or 데이터 가져오기
        viewModelScope.launch {
            _uiState.update { prev ->
                prev.copy(
                    header = "내일배움캠프 게시판",
                    title = "compose 강의",
                    content = "MVVM 디자인 패턴과 compose",
                    user = "장세진",
                    viewCount = 60000,
                )
            }
        }
    }
}