package com.teamsparta.togahter.domain.exception

data class InvalidCredentialException(
    override val message: String? = "잘못된 접근입니다."): RuntimeException(message)

