package com.jess.nbcamp.compose4.user.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jess.nbcamp.compose4.user.user.UserEntity
import com.jess.nbcamp.compose4.user.user.UserManager
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState.empty())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<SignUpEvent>()
    val event: SharedFlow<SignUpEvent> = _event.asSharedFlow()

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

    fun onClickSignUp() = viewModelScope.launch {

        val name = uiState.value.name
        val id = uiState.value.id
        val password = uiState.value.password

        if (name.isBlank()
            || id.isBlank()
            || password.isBlank()
        ) {
            _event.emit(SignUpEvent.NeedInputElement)
            return@launch
        }

        val userEntity = UserEntity(
            name = uiState.value.name,
            id = uiState.value.id,
            password = uiState.value.password,
        )

        UserManager.list = UserManager.list.toMutableList().apply {
            add(userEntity)
        }

        _event.emit(SignUpEvent.FinishAndResultOk)
    }

}