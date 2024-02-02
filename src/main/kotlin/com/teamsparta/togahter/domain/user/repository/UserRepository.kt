package com.teamsparta.togahter.domain.user.repository

import com.teamsparta.togahter.domain.user.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, Long> {

    fun existsByProfileEntityEmail(email: String): Boolean

    fun findByProfileEntityEmail(email:String) : UserEntity?
}