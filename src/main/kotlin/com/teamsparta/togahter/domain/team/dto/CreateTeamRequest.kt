package com.teamsparta.togahter.domain.team.dto

data class CreateTeamRequest(
    val name: String,
    val region: String,
    val interest: String,
    val introduction: String,
    val rule: String
)
