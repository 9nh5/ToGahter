package com.teamsparta.togahter.domain.user.dto

data class ProfileResponse(
    val id: Long,
    val name: String,
    val age: Long,
    val phone: String,
    val email: String,
    val nickname: String,
    val interest: String,
    val region: String,
    val introduction: String,
    val role: String
)
