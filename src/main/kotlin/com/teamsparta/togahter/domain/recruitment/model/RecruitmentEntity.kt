package com.teamsparta.togahter.domain.recruitment.model

import com.teamsparta.togahter.domain.recruitment.dto.RecruitmentResponse
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "recruitment")
class RecruitmentEntity(

    @Column(name = "title")
    var title: String,

    @Column(name = "description")
    var description: String,

    @Column(name = "status", nullable = false)
    var status: Boolean = false,

    @Column(name = "type")
    var type: String,

    @Column(name = "region")
    var region: String,

    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//UUID 조사해보고 선택
    var id: Long? = null
}

fun RecruitmentEntity.getAllList(): RecruitmentResponse {
    return RecruitmentResponse(
        id = id!!,
        title = title,
        description = description,
        status = status,
        type = type,
        region = region,
        createdAt = createdAt
    )
}