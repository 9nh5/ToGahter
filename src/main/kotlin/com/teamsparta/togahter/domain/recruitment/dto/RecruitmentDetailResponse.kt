package com.teamsparta.togahter.domain.recruitment.dto

import com.teamsparta.togahter.domain.recruitment.model.RecruitmentEntity
import java.time.LocalDateTime

data class RecruitmentDetailResponse(
    val id: Long,
    val title: String,
    val nickname: String,
    val description: String,
    val status: Boolean,
    val region: String,
    val type: String,
    val createdAt: LocalDateTime
//    val comment: List<CommentResponse>//나중에 댓글 추가
)