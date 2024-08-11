package com.jess.nbcamp.compose4.user.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jess.nbcamp.compose4.user.user.UserManager
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SignInUiState.empty())
    val uiState: StateFlow<SignInUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<SignInEvent>()
    val event: SharedFlow<SignInEvent> = _event.asSharedFlow()

    fun onChangedId(id: String) {
        _uiState.update { prev ->
            prev.copy(id = id)
        }
    }

    fun onChangedPassword(password: String) {
        _uiState.update { prev ->
            prev.copy(password = password)
        }
    }

    fun onClickLogin() = viewModelScope.launch {
        val id = uiState.value.id
        val password = uiState.value.password

        if (id.isBlank() || password.isBlank()) {
            _event.emit(SignInEvent.NeedInputElement)
            return@launch
        }

        val result = UserManager.list.find {
            it.id == id && it.password == password
        }

        _event.emit(
            if (result == null) {
                SignInEvent.NotFoundUser
            } else {
                SignInEvent.MoveUserScreen
            }
        )
    }


}