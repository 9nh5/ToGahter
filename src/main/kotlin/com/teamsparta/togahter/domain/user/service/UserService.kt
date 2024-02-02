package com.teamsparta.togahter.domain.user.service

import com.teamsparta.togahter.domain.security.UserPrincipal
import com.teamsparta.togahter.domain.user.dto.*

interface UserService {

    fun signUp(request: SignUpRequest): UserResponse

    fun login(request: LoginRequest): LoginResponse

    fun getProfile(userPrincipal: UserPrincipal): ProfileResponse

    fun updateProfile(userPrincipal: UserPrincipal, updateProfileRequest: UpdateProfileRequest): ProfileResponse

    fun deleteProfile(userPrincipal: UserPrincipal)
}