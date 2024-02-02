package com.teamsparta.togahter.domain.recruitment.model

import com.teamsparta.togahter.domain.recruitment.dto.RecruitmentDetailResponse
import com.teamsparta.togahter.domain.recruitment.dto.RecruitmentResponse
import com.teamsparta.togahter.domain.user.model.UserEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "recruitment")
class RecruitmentEntity(

    @Column(name = "title")
    var title: String,

    @Column(name = "description")
    var description: String,

    @Column(name = "status")
    var status: Boolean = false,

    @Column(name = "type")
    var type: String,

    @Column(name = "region")
    var region: String,

    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: UserEntity

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//UUID 조사해보고 선택
    var id: Long? = null

    fun deadLine(){
        status = !status
    }
}


fun RecruitmentEntity.toResponse(): RecruitmentResponse {
    return RecruitmentResponse(
        id = id!!,
        title = title,
        nickname = user.profileEntity.nickname,
        description = description,
        status = status,
        type = type,
        region = region,
        createdAt = createdAt
    )
}

fun RecruitmentEntity.toGetting(): RecruitmentDetailResponse {
    return RecruitmentDetailResponse(
        id = id!!,
        title = title,
        nickname = user.profileEntity.nickname,
        description = description,
        status = status,
        region = region,
        type = type,
        createdAt = createdAt
//        comments = comments.map { it.toComments() }
    )
}

