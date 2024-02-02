package com.teamsparta.togahter.domain.recruitment.service

import com.teamsparta.togahter.domain.recruitment.dto.CreateRecruitmentRequest
import com.teamsparta.togahter.domain.recruitment.dto.RecruitmentDetailResponse
import com.teamsparta.togahter.domain.recruitment.dto.RecruitmentResponse
import com.teamsparta.togahter.domain.recruitment.dto.UpdateRecruitmentRequest
import com.teamsparta.togahter.domain.security.UserPrincipal

interface RecruitmentService {

    fun getAllRecruitmentList(): List<RecruitmentResponse>

    fun getRecruitment(recruitmentId: Long): RecruitmentDetailResponse

    fun createRecruitment(request: CreateRecruitmentRequest, userPrincipal: UserPrincipal): RecruitmentResponse

    fun updateRecruitment(request: UpdateRecruitmentRequest, recruitmentId: Long, userPrincipal: UserPrincipal): RecruitmentResponse

    fun updateRecruitmentStatus(recruitmentId: Long, userPrincipal: UserPrincipal): RecruitmentResponse

    fun deleteRecruitment(userPrincipal: UserPrincipal, recruitmentId: Long)
}