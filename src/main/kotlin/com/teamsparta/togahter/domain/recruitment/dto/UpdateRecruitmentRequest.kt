package com.teamsparta.togahter.domain.recruitment.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Pattern

@Schema(description = "팀원 모집글 수정")
data class UpdateRecruitmentRequest(

    @Schema(description = "제목", example = "팀원 모집글 수정")
    @field: Pattern(regexp = "^{2,500}")
    val title: String,

    @Schema(description = "설명", example = "팀원 모집합니다 수정")
    @field: Pattern(regexp = "^{1,5000}")
    val description: String,

    @Schema(description = "지역", example = "서울")
    val region: String,

    @Schema(description = "분야", example = "축구")
    val type: String
)
