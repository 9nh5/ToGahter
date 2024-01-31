package com.teamsparta.togahter.domain.recruitment.controller

import com.teamsparta.togahter.domain.recruitment.dto.RecruitmentResponse
import com.teamsparta.togahter.domain.recruitment.service.RecruitmentService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/recruitments")
@RestController
class RecruitmentController(
    private val recruitmentService: RecruitmentService
) {

    @Operation(summary = "팀원 모집 게시")
    @GetMapping
    fun getAllRecruitmentList(): ResponseEntity<List<RecruitmentResponse>>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(recruitmentService.getAllRecruitmentList())
    }
}