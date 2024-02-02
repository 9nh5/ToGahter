package com.teamsparta.togahter.domain.team.model

import com.teamsparta.togahter.domain.team.dto.TeamResponse
import jakarta.persistence.*

@Entity
@Table(name = "team")
class TeamEntity(

    @Embedded
    var teamProfileEntity: TeamProfileEntity
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun TeamEntity.toTeam(): TeamResponse {
    return TeamResponse(
        id = id!!,
        name = teamProfileEntity.name,
        region = teamProfileEntity.region,
        introduction = teamProfileEntity.introduction,
        interest = teamProfileEntity.interest,
        rule = teamProfileEntity.rule
    )
}