package com.teamsparta.togahter.domain.team.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class TeamProfileEntity(

    @Column(name = "name")
    var name: String,

    @Column(name = "region")
    var region: String,

    @Column(name = "interest")
    var interest: String,

    @Column(name = "introduction")
    var introduction: String,

    @Column(name = "rule")
    var rule: String
)