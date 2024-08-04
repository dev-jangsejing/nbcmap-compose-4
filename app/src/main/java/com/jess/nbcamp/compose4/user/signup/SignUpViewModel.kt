package com.jess.nbcamp.compose4.user.signup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignUpViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState.empty())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    fun onChangedId(id: String) {
        _uiState.update { prev ->
            prev.copy(id = id)
        }
    }

    fun onChangedName(name: String) {
        _uiState.update { prev ->
            prev.copy(name = name)
        }
    }

    fun onChangedPassword(password: String) {
        _uiState.update { prev ->
            prev.copy(password = password)
        }
    }
}