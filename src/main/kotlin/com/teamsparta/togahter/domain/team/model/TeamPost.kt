package com.teamsparta.togahter.domain.team.model

import com.teamsparta.togahter.domain.team.dto.TeamPostResponse
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "team_post")
class TeamPost(

    @Column(name = "title")
    var title: String,

    @Column(name = "description")
    var description: String,

    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now(),
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun TeamPost.toPost(): TeamPostResponse {
    return TeamPostResponse(
        title = title,
        description = description,
        createdAt = createdAt
    )
}