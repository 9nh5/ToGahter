package com.teamsparta.togahter.domain.user.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern

@Schema(description = "로그인")
data class LoginRequest(

    @Schema(description = "이메일", example = "email@email.com")
    @field:Email(message = "이메일 형식이 아닙니다.")
    val email: String,

    @Schema(description = "비밀번호", example = "password")
    @field:Pattern(regexp = "^{4,20}")
    val password: String,
)
