package com.jess.nbcamp.compose4.user.signin

sealed interface SignInEvent {
    data object NeedInputElement : SignInEvent
    data object MoveUserScreen : SignInEvent
    data object NotFoundUser : SignInEvent
}