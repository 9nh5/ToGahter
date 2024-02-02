package com.teamsparta.togahter.domain.user.service

import com.teamsparta.togahter.domain.exception.InvalidCredentialException
import com.teamsparta.togahter.domain.exception.NotAuthorizationException
import com.teamsparta.togahter.domain.exception.UserNotFoundException
import com.teamsparta.togahter.domain.security.TokenProvider
import com.teamsparta.togahter.domain.security.UserPrincipal
import com.teamsparta.togahter.domain.user.dto.*
import com.teamsparta.togahter.domain.user.model.*
import com.teamsparta.togahter.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
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
    override fun getProfile(userPrincipal: UserPrincipal): ProfileResponse {
        val user = userRepository.findByIdOrNull(userPrincipal.id)?:
        throw UserNotFoundException("User", null)
        return user.toProfile()
    }

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

    @Transactional
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

    @Transactional
    override fun updateProfile(
        userPrincipal: UserPrincipal,
        updateProfileRequest: UpdateProfileRequest
    ): ProfileResponse {
        val user = userRepository.findByIdOrNull(userPrincipal.id)?:
        throw UserNotFoundException("User", null)
        if (user.id != userPrincipal.id) throw NotAuthorizationException()
        user.profileEntity = ProfileEntity(
            name = updateProfileRequest.name,
            phone = updateProfileRequest.phone,
            age = updateProfileRequest.age,
            region = updateProfileRequest.region,
            email = updateProfileRequest.email,
            nickname = updateProfileRequest.nickname,
            interest = updateProfileRequest.interest,
            introduction = updateProfileRequest.introduction,
            password = user.profileEntity.password
        )
        return userRepository.save(user).toProfile()
    }

    @Transactional
    override fun deleteProfile(userPrincipal: UserPrincipal) {
        val user = userRepository.findByIdOrNull(userPrincipal.id)?:
        throw UserNotFoundException("User", null)
        if (user.id != userPrincipal.id) throw NotAuthorizationException()
        userRepository.delete(user)
    }

}