package com.teamsparta.togahter.domain.user.model

import com.teamsparta.togahter.domain.recruitment.model.RecruitmentEntity
import com.teamsparta.togahter.domain.team.model.TeamEntity
import com.teamsparta.togahter.domain.user.dto.ProfileResponse
import com.teamsparta.togahter.domain.user.dto.UserResponse
import jakarta.persistence.*

@Entity
@Table(name = "app_user")
class UserEntity(

    @Embedded
    var profileEntity: ProfileEntity,

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    var role: UserRole,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    var recruitments: MutableList<RecruitmentEntity> = mutableListOf(),

    @OneToMany(fetch = FetchType.LAZY)
    var teams: MutableList<TeamEntity> = mutableListOf()

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun UserEntity.signUp(): UserResponse {
    return UserResponse(
        id = id!!,
        email = profileEntity.email,
        name = profileEntity.name,
        nickname = profileEntity.nickname,
        password = profileEntity.password,
        phone = profileEntity.phone,
        age = profileEntity.age,
        region = profileEntity.region,
        interest = profileEntity.interest,
        introduction = profileEntity.introduction,
        role = role.name
    )
}

fun UserEntity.toProfile(): ProfileResponse {
    return ProfileResponse(
        id = id!!,
        email = profileEntity.email,
        name = profileEntity.name,
        nickname = profileEntity.nickname,
        phone = profileEntity.phone,
        age = profileEntity.age,
        region = profileEntity.region,
        interest = profileEntity.interest,
        introduction = profileEntity.introduction,
        role = role.name
    )
}