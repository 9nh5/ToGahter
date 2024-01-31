package com.teamsparta.togahter.domain.recruitment.service

import com.teamsparta.togahter.domain.recruitment.dto.CreateRecruitmentRequest
import com.teamsparta.togahter.domain.recruitment.dto.RecruitmentResponse

interface RecruitmentService {

    fun getAllRecruitmentList(): List<RecruitmentResponse>

    fun createRecruitment(request: CreateRecruitmentRequest): RecruitmentResponse
}