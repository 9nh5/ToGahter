package com.teamsparta.togahter.domain.recruitment.repository

import com.teamsparta.togahter.domain.recruitment.model.RecruitmentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface RecruitmentRepository: JpaRepository<RecruitmentEntity, Long> {
}