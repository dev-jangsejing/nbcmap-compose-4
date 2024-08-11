package com.jess.nbcamp.compose4.user.signup

sealed interface SignUpEvent {
    data object FinishAndResultOk : SignUpEvent
    data object NeedInputElement : SignUpEvent
}