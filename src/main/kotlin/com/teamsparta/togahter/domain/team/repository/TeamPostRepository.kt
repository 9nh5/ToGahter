package com.teamsparta.togahter.domain.team.repository

import com.teamsparta.togahter.domain.team.model.TeamPost
import org.springframework.data.jpa.repository.JpaRepository

interface TeamPostRepository : JpaRepository<TeamPost, Long> {
}