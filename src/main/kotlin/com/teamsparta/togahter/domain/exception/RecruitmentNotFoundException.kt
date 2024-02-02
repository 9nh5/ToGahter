package com.teamsparta.togahter.domain.exception

data class RecruitmentNotFoundException(val postName: String, val id: Long?) :
    RuntimeException("사용자 $postName, id: $id 를 찾을 수 없습니다.")