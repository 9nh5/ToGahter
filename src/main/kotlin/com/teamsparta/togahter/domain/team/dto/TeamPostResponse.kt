package com.teamsparta.togahter.domain.team.dto

import java.time.LocalDateTime

data class TeamPostResponse(
    val title: String,
    val description: String,
    val createdAt: LocalDateTime
)
