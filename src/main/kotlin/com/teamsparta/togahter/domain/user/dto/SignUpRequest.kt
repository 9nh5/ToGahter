package com.teamsparta.togahter.domain.user.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern

@Schema(description = "회원가입")
data class SignUpRequest(

    @Schema(description = "이름", example = "이름")
    val name: String,

    @Schema(description = "전화번호", example = "010-0000-0000")
    val phone: String,

    @Schema(description = "나이", example = "20")
    val age: Long,

    @Schema(description = "지역", example = "서울")
    val region: String,

    @Schema(description = "이메일", example = "email@email.com")
    @field:Email(message = "이메일 형식이 아닙니다.")
    val email: String,

    @Schema(description = "비밀번호", example = "password")
    @field:Pattern(regexp = "^{4,20}")
    val password: String,

    @Schema(description = "닉네임", example = "Nickname12")
    @field:Pattern(
        regexp = "^[a-zA-Z0-9!]{4,10}",
        message = "닉네임은 4~10자 이하, 알파벳 대소문자, 숫자가 포함되어야 합니다.")
    val nickname: String,

    @Schema(description = "관심 분야", example = "축구, 게임")
    val interest: String,

    @Schema(description = "소개", example = "소개글 입니다")
    val introduction: String,

)
