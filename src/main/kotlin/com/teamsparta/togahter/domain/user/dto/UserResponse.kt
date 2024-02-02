package com.teamsparta.togahter.domain.user.dto

data class UserResponse(
    val id: Long,
    val name: String,
    val age: Long,
    val phone: String,
    val email: String,
    val nickname: String,
    val interest: String,
    val region: String,
    val password: String,
    val introduction: String,
    val role: String
)
