package com.jess.nbcamp.compose4.user.user

object UserManager {
    var list: List<UserEntity> = listOf()
}

data class UserEntity(
    val name: String,
    val id: String,
    val password: String,
)