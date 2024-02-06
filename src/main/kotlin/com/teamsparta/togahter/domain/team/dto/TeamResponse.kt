package com.teamsparta.togahter.domain.team.dto

import java.time.LocalDateTime

data class TeamResponse(
    val id: Long,
    val name: String,
    val region: String,
    val interest: String,
    val introduction: String,
    val rule: String,
    val createdAt: LocalDateTime,
    val leader: Long

)
