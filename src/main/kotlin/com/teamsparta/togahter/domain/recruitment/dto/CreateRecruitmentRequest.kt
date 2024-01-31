package com.teamsparta.togahter.domain.recruitment.dto

data class CreateRecruitmentRequest(
    val title: String,
    val description: String,
    val status: Boolean,
    val region: String,
    val type: String,

)
