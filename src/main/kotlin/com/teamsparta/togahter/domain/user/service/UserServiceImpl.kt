package com.teamsparta.togahter.domain.user.service

import com.teamsparta.togahter.domain.exception.InvalidCredentialException
import com.teamsparta.togahter.domain.exception.UserNotFoundException
import com.teamsparta.togahter.domain.security.TokenProvider
import com.teamsparta.togahter.domain.user.dto.LoginRequest
import com.teamsparta.togahter.domain.user.dto.LoginResponse
import com.teamsparta.togahter.domain.user.dto.SignUpRequest
import com.teamsparta.togahter.domain.user.dto.UserResponse
import com.teamsparta.togahter.domain.user.model.ProfileEntity
import com.teamsparta.togahter.domain.user.model.UserEntity
import com.teamsparta.togahter.domain.user.model.UserRole
import com.teamsparta.togahter.domain.user.model.signUp
import com.teamsparta.togahter.domain.user.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder,
    private val tokenProvider: TokenProvider
): UserService {

    @Transactional
    override fun signUp(request: SignUpRequest): UserResponse {

        if (userRepository.existsByProfileEntityEmail(request.email))
            throw IllegalStateException("이미 사용중인 이메일입니다.")

        return userRepository.save(
            UserEntity(

                profileEntity = ProfileEntity(
                    email = request.email,
                    name = request.name,
                    password = encoder.encode(request.password),
                    nickname = request.nickname,
                    age = request.age,
                    phone = request.phone,
                    interest = request.interest,
                    region = request.region,
                    introduction = request.introduction),
                role = UserRole.USER
                )
            ).signUp()
    }

    override fun login(request: LoginRequest): LoginResponse {

        val user = userRepository.findByProfileEntityEmail(request.email)
            ?: throw UserNotFoundException("User", null)

        if(user.role.toString() != "USER")
            throw InvalidCredentialException("잘못된 접근입니다.")

        if (!encoder.matches(request.password, user.profileEntity.password)) {
            throw InvalidCredentialException("로그인 정보가 일치하지 않습니다.")
        }

        return LoginResponse(
            accessToken = tokenProvider.generateAccessToken(
                subject = user.id.toString(),
                email = user.profileEntity.email,
                nickname = user.profileEntity.nickname,
                role = user.role.name
            )
        )
    }


}