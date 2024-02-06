package com.teamsparta.togahter.domain.team.controller

import com.teamsparta.togahter.domain.security.UserPrincipal
import com.teamsparta.togahter.domain.team.dto.*
import com.teamsparta.togahter.domain.team.service.TeamService
import com.teamsparta.togahter.domain.user.service.UserService
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/teams")
@RestController
class TeamController(
    private val userService: UserService,
    private val teamService: TeamService
) {

    @PostMapping
    fun createTeam(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody createTeamRequest: CreateTeamRequest): ResponseEntity<TeamResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(teamService.createTeam(userPrincipal, createTeamRequest))
    }

    @PostMapping("/{leader}")
    fun createTeamPost(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable leader: Long,
        @RequestBody createTeamPostRequest: CreateTeamPostRequest
    ): ResponseEntity<TeamPostResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(teamService.createTeamPost(userPrincipal, createTeamPostRequest, leader))
    }

    @PostMapping("/{teamid}/team/{leader}")
    fun addMember(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable teamid: Long,
        @PathVariable leader: Long,
        @RequestBody addMemberRequest: AddMemberRequest
    ): ResponseEntity<AddMemberResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(teamService.addMember(userPrincipal, addMemberRequest, leader, teamid))
    }

}