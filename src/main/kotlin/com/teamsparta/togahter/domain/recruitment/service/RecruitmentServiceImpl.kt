package com.teamsparta.togahter.domain.recruitment.service

import com.teamsparta.togahter.domain.recruitment.dto.CreateRecruitmentRequest
import com.teamsparta.togahter.domain.recruitment.dto.RecruitmentResponse
import com.teamsparta.togahter.domain.recruitment.model.getAllList
import com.teamsparta.togahter.domain.recruitment.repository.RecruitmentRepository
import org.springframework.stereotype.Service

@Service
class RecruitmentServiceImpl(
    private val recruitmentRepository: RecruitmentRepository
): RecruitmentService {

    override fun getAllRecruitmentList(): List<RecruitmentResponse> {
        return recruitmentRepository.findAll().map { it.getAllList() }
    }

    override fun createRecruitment(request: CreateRecruitmentRequest): RecruitmentResponse {
        TODO("Not yet implemented")
    }
}