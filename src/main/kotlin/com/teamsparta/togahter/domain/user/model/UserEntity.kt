package com.teamsparta.togahter.domain.user.model

import com.teamsparta.togahter.domain.user.dto.UserResponse
import jakarta.persistence.*

@Entity
@Table(name = "app_user")
class UserEntity(

    @Embedded
    var profileEntity: ProfileEntity,

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    var role: UserRole

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