package com.teamsparta.togahter.domain.team.service

import com.teamsparta.togahter.domain.exception.UserNotFoundException
import com.teamsparta.togahter.domain.security.UserPrincipal
import com.teamsparta.togahter.domain.team.dto.CreateTeamRequest
import com.teamsparta.togahter.domain.team.dto.TeamResponse
import com.teamsparta.togahter.domain.team.model.TeamEntity
import com.teamsparta.togahter.domain.team.model.TeamProfileEntity
import com.teamsparta.togahter.domain.team.model.toTeam
import com.teamsparta.togahter.domain.team.repository.TeamRepository
import com.teamsparta.togahter.domain.user.model.UserEntity
import com.teamsparta.togahter.domain.user.model.UserRole
import com.teamsparta.togahter.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TeamServiceImpl(
    private val teamRepository: TeamRepository,
    private val userRepository: UserRepository
): TeamService {

    override fun createTeam(userPrincipal: UserPrincipal, request: CreateTeamRequest): TeamResponse {
        val user = userRepository.findByIdOrNull(userPrincipal.id)?: throw UserNotFoundException("User", null)
        if (teamRepository.existsByTeamProfileEntityName(request.name)) {
            throw IllegalStateException("이미 사용중인 팀명입니다.")
        }
//        user.role = UserRole.LEADER
        return teamRepository.save<TeamEntity?>(
            TeamEntity(
                teamProfileEntity = TeamProfileEntity(
                    name = request.name,
                    region = request.region,
                    introduction = request.introduction,
                    interest = request.interest,
                    rule = request.rule
                )
            )
        ).toTeam()
    }


}