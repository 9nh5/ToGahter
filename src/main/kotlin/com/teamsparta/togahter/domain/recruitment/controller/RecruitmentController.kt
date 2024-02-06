package com.teamsparta.togahter.domain.recruitment.controller

import com.teamsparta.togahter.domain.recruitment.dto.CreateRecruitmentRequest
import com.teamsparta.togahter.domain.recruitment.dto.RecruitmentDetailResponse
import com.teamsparta.togahter.domain.recruitment.dto.RecruitmentResponse
import com.teamsparta.togahter.domain.recruitment.service.RecruitmentService
import com.teamsparta.togahter.domain.security.UserPrincipal
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/recruitments")
@RestController
class RecruitmentController(
    private val recruitmentService: RecruitmentService
) {

    @Operation(summary = "팀원 모집 게시글 조회")
    @GetMapping
    fun getAllRecruitmentList(): ResponseEntity<List<RecruitmentResponse>>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(recruitmentService.getAllRecruitmentList())
    }

    @Operation(summary = "게시글 상세 조회")
    @GetMapping("/{recruitmentId}")
    fun getRecruitment(@PathVariable recruitmentId: Long): ResponseEntity<RecruitmentDetailResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(recruitmentService.getRecruitment(recruitmentId))
    }

//    @PreAuthorize("hasRole('LEADER') or hasRole('MEMBER')")
    @Operation(summary = "팀원 모집 게시글 작성")
    @PostMapping("/{leader}")
    fun createRecruitment(
        @Valid
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable leader: Long,
        @RequestBody createRecruitmentRequest: CreateRecruitmentRequest
    ): ResponseEntity<RecruitmentResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(recruitmentService.createRecruitment(createRecruitmentRequest, userPrincipal, leader))
    }
}