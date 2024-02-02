package com.teamsparta.togahter.domain.recruitment.service

import com.teamsparta.togahter.domain.exception.NotAuthorizationException
import com.teamsparta.togahter.domain.exception.RecruitmentNotFoundException
import com.teamsparta.togahter.domain.exception.UserNotFoundException
import com.teamsparta.togahter.domain.recruitment.dto.CreateRecruitmentRequest
import com.teamsparta.togahter.domain.recruitment.dto.RecruitmentDetailResponse
import com.teamsparta.togahter.domain.recruitment.dto.RecruitmentResponse
import com.teamsparta.togahter.domain.recruitment.dto.UpdateRecruitmentRequest
import com.teamsparta.togahter.domain.recruitment.model.RecruitmentEntity
import com.teamsparta.togahter.domain.recruitment.model.toGetting
import com.teamsparta.togahter.domain.recruitment.model.toResponse
import com.teamsparta.togahter.domain.recruitment.repository.RecruitmentRepository
import com.teamsparta.togahter.domain.security.UserPrincipal
import com.teamsparta.togahter.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RecruitmentServiceImpl(
    private val recruitmentRepository: RecruitmentRepository,
    private val userRepository: UserRepository
): RecruitmentService {

    @Transactional
    override fun getAllRecruitmentList(): List<RecruitmentResponse> {
        return recruitmentRepository.findAll().map { it.toResponse() }
    }

    @Transactional
    override fun getRecruitment(recruitmentId: Long): RecruitmentDetailResponse {
        val recruitment = recruitmentRepository.findByIdOrNull(recruitmentId)
            ?: throw RecruitmentNotFoundException("Recruitment", recruitmentId)
        return recruitment.toGetting()
    }

    @Transactional
    override fun createRecruitment(
        request: CreateRecruitmentRequest,
        userPrincipal: UserPrincipal,
        ): RecruitmentResponse {
        val user = userRepository.findByIdOrNull(userPrincipal.id)?: throw UserNotFoundException("User", null)
        return recruitmentRepository.save<RecruitmentEntity?>(
                RecruitmentEntity(
                    user = user,
                    title = request.title,
                    description = request.description,
                    region = request.region,
                    type = request.type
                    )
                ).toResponse()
    }

    @Transactional
    override fun updateRecruitment(request: UpdateRecruitmentRequest, recruitmentId: Long, userPrincipal: UserPrincipal): RecruitmentResponse {
        userRepository.findByIdOrNull(userPrincipal.id)?: throw UserNotFoundException("User", null)
        val recruitment = recruitmentRepository.findByIdOrNull(recruitmentId)?: throw RecruitmentNotFoundException("Recruitment", recruitmentId)
        if (recruitment.user.id != userPrincipal.id) throw NotAuthorizationException()
        val (title, description, region, type) = request

        recruitment.title = title
        recruitment.description = description
        recruitment.region = region
        recruitment.type = type

        return recruitmentRepository.save(recruitment).toResponse()
    }

    @Transactional
    override fun updateRecruitmentStatus(recruitmentId: Long, userPrincipal: UserPrincipal): RecruitmentResponse {
        userRepository.findByIdOrNull(userPrincipal.id)?: throw UserNotFoundException("User", null)
        val recruitment = recruitmentRepository.findByIdOrNull(recruitmentId)?: throw RecruitmentNotFoundException("Recruitment", recruitmentId)
        if (recruitment.user.id != userPrincipal.id) throw NotAuthorizationException()

        recruitment.deadLine()

        return recruitmentRepository.save(recruitment).toResponse()
    }

    @Transactional
    override fun deleteRecruitment(userPrincipal: UserPrincipal, recruitmentId: Long) {
        userRepository.findByIdOrNull(userPrincipal.id)?: throw UserNotFoundException("User", null)
        val recruitment = recruitmentRepository.findByIdOrNull(recruitmentId)?: throw RecruitmentNotFoundException("Recruitment", recruitmentId)
        if (recruitment.user.id != userPrincipal.id) throw NotAuthorizationException()
        recruitmentRepository.delete(recruitment)
    }

}