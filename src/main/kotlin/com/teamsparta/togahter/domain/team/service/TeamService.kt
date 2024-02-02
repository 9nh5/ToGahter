package com.teamsparta.togahter.domain.team.service

import com.teamsparta.togahter.domain.security.UserPrincipal
import com.teamsparta.togahter.domain.team.dto.CreateTeamRequest
import com.teamsparta.togahter.domain.team.dto.TeamResponse

interface TeamService {

    fun createTeam(userPrincipal: UserPrincipal, request: CreateTeamRequest): TeamResponse
}