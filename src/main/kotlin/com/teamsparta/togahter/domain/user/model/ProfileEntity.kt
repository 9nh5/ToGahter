package com.teamsparta.togahter.domain.user.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class ProfileEntity(

    @Column(name = "email")
    var email: String,

    @Column(name = "name")
    var name: String,

    @Column(name = "nickname")
    var nickname: String,

    @Column(name = "password")
    var password: String,

    @Column(name = "phone")
    var phone: String,

    @Column(name = "age")
    var age: Long,

    @Column(name = "region")
    var region: String,

    @Column(name = "interest")
    var interest: String,

    @Column(name = "introduction")
    var introduction: String,
)
