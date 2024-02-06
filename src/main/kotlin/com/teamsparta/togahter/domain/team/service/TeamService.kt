package com.teamsparta.togahter.domain.team.service

import com.teamsparta.togahter.domain.security.UserPrincipal
import com.teamsparta.togahter.domain.team.dto.*

interface TeamService {

    fun createTeam(userPrincipal: UserPrincipal, request: CreateTeamRequest): TeamResponse

    fun createTeamPost(userPrincipal: UserPrincipal, request: CreateTeamPostRequest, leader: Long): TeamPostResponse

    fun addMember(userPrincipal: UserPrincipal,request: AddMemberRequest, teamId: Long, leader: Long): AddMemberResponse
}