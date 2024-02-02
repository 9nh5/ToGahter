package com.teamsparta.togahter.domain.exception

data class UserNotFoundException(val userName: String, val id: Long?) :
        RuntimeException("사용자 $userName, id: $id 를 찾을 수 없습니다.")