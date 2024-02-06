package com.teamsparta.togahter.domain.team.service

import com.teamsparta.togahter.domain.exception.NotAuthorizationException
import com.teamsparta.togahter.domain.exception.UserNotFoundException
import com.teamsparta.togahter.domain.security.UserPrincipal
import com.teamsparta.togahter.domain.team.dto.*
import com.teamsparta.togahter.domain.team.model.*
import com.teamsparta.togahter.domain.team.repository.TeamPostRepository
import com.teamsparta.togahter.domain.team.repository.TeamRepository
import com.teamsparta.togahter.domain.user.model.UserEntity
import com.teamsparta.togahter.domain.user.model.UserRole
import com.teamsparta.togahter.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TeamServiceImpl(
    private val teamRepository: TeamRepository,
    private val userRepository: UserRepository,
    private val teamPostRepository: TeamPostRepository
): TeamService {

    @Transactional
    override fun createTeam(userPrincipal: UserPrincipal, request: CreateTeamRequest): TeamResponse {
        val user = userRepository.findByIdOrNull(userPrincipal.id) ?: throw UserNotFoundException("User", null)
        if (teamRepository.existsByTeamProfileEntityName(request.name)) {
            throw IllegalStateException("이미 사용중인 팀명입니다.")
        }

        return teamRepository.save<TeamEntity?>(
            TeamEntity(
                teamProfileEntity = TeamProfileEntity(
                    name = request.name,
                    region = request.region,
                    introduction = request.introduction,
                    interest = request.interest,
                    rule = request.rule
                ), leader = user.id!!,
                user = user
            )
        ).toTeam()
    }

    @Transactional
    override fun createTeamPost(
        userPrincipal: UserPrincipal,
        request: CreateTeamPostRequest,
        leader: Long
    ): TeamPostResponse {
        val user = userRepository.findByIdOrNull(userPrincipal.id) ?: throw UserNotFoundException("User", null)
        val team = teamRepository.findByLeader(leader) ?: throw NotAuthorizationException()
        if (user.id != team.leader) {
            throw NotAuthorizationException()
        }

        return teamPostRepository.save<TeamPost?>(
            TeamPost(
                title = request.title,
                description = request.description
            )
        ).toPost()
    }

    @Transactional
    override fun addMember(userPrincipal: UserPrincipal, request: AddMemberRequest, teamId: Long, leader: Long): AddMemberResponse {
        val user = userRepository.findByIdOrNull(userPrincipal.id) ?: throw UserNotFoundException("User", null)
        val team = teamRepository.findByLeader(leader) ?: throw NotAuthorizationException()
        if (user.id != team.leader) {
            throw NotAuthorizationException()
        }

        user.id
        user.profileEntity.name

        return teamRepository.save(team).toAdd()


    }

}