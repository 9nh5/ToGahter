package com.teamsparta.togahter.domain.user.service

import com.teamsparta.togahter.domain.user.dto.LoginRequest
import com.teamsparta.togahter.domain.user.dto.LoginResponse
import com.teamsparta.togahter.domain.user.dto.SignUpRequest
import com.teamsparta.togahter.domain.user.dto.UserResponse

interface UserService {

    fun signUp(request: SignUpRequest): UserResponse

    fun login(request: LoginRequest): LoginResponse
}