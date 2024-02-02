package com.teamsparta.togahter.domain.recruitment.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Pattern

@Schema(description = "팀원 모집 글 생성")
data class CreateRecruitmentRequest(

    @Schema(description = "제목")
    @field: Pattern(
        regexp = "^{2,500}"
    )
    val title: String,

    val description: String,
    val status: Boolean,
    val region: String,
    val type: String,

)
