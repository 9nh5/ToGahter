package com.teamsparta.togahter.domain.team.model

import com.teamsparta.togahter.domain.recruitment.model.RecruitmentEntity
import com.teamsparta.togahter.domain.team.dto.AddMemberResponse
import com.teamsparta.togahter.domain.team.dto.TeamResponse
import com.teamsparta.togahter.domain.user.model.UserEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "team")
class TeamEntity(

    @Embedded
    var teamProfileEntity: TeamProfileEntity,

    @Column(name = "leader")
    var leader: Long,

    @Column(name = "createdAt")
    var createdAt: LocalDateTime = LocalDateTime.now(),

//    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
//    var user: MutableList<RecruitmentEntity> = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member")
    var user: UserEntity
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
        rule = teamProfileEntity.rule,
        createdAt = LocalDateTime.now(),
        leader = leader
    )
}

fun TeamEntity.toAdd(): AddMemberResponse {
    return AddMemberResponse(
        userId = user.id!!,
        userName = user.profileEntity.name
    )
}