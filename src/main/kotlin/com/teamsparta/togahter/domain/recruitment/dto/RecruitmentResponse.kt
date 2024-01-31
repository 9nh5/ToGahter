package com.teamsparta.togahter.domain.recruitment.dto

import java.time.LocalDateTime

data class RecruitmentResponse(
    val id: Long,
    val title: String,
    val description: String,
    val status: Boolean,
    val region: String,
    val type: String,
    val createdAt: LocalDateTime
)
