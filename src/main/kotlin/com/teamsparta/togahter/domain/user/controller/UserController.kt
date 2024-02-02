package com.teamsparta.togahter.domain.user.controller

import com.teamsparta.togahter.domain.security.UserPrincipal
import com.teamsparta.togahter.domain.user.dto.*
import com.teamsparta.togahter.domain.user.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@Tag(name = "users", description = "사용자 API")
@RequestMapping("/api/users")
@RestController
class UserController(
    private val userService: UserService
) {

    @PreAuthorize("isAnonymous()")
    @Operation(summary = "로그인")
    @PostMapping("/login")
    fun login(
        @RequestBody loginRequest: LoginRequest
    ): ResponseEntity<LoginResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.login(loginRequest))
    }

    @PreAuthorize("isAnonymous()")
    @Operation(summary = "사용자 회원가입")
    @PostMapping("/signup")
    fun signUp(
        @Valid
        @RequestBody signUpRequest: SignUpRequest
    ): ResponseEntity<UserResponse> {
        return  ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.signUp(signUpRequest))
    }

    @Operation(summary = "내 프로필 조회")
    @GetMapping("/{userid}/profile")
    fun getProfile(
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<ProfileResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.getProfile(userPrincipal))
    }

    @Operation(summary = "프로필 수정")
    @PutMapping("/{userid}")
    fun updateProfilet(
        @Valid
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody request: UpdateProfileRequest
    ): ResponseEntity<ProfileResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.updateProfile(userPrincipal, request))
    }

    @Operation(summary = "프로필 삭제")
    @DeleteMapping("/{userid}")
    fun deleteUserProfile(
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<Unit>{
        userService.deleteProfile(userPrincipal)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}