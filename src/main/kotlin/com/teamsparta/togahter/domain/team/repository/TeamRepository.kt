package com.teamsparta.togahter.domain.team.repository

import com.teamsparta.togahter.domain.team.model.TeamEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TeamRepository : JpaRepository<TeamEntity, Long>{

    fun existsByTeamProfileEntityName(name: String): Boolean

    fun findByLeader(leader: Long): TeamEntity?
}

