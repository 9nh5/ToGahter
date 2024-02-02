package com.teamsparta.togahter.domain.user.controller

import com.teamsparta.togahter.domain.user.dto.LoginRequest
import com.teamsparta.togahter.domain.user.dto.LoginResponse
import com.teamsparta.togahter.domain.user.dto.SignUpRequest
import com.teamsparta.togahter.domain.user.dto.UserResponse
import com.teamsparta.togahter.domain.user.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
}