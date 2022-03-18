package com.soon83.workmanagement.domain

import javax.persistence.*

@Entity
class User(

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(length = 15)
    var name: String,

    @Enumerated(EnumType.STRING)
    @Column(length = 31)
    val gender: Gender,

    val age: Int = 0,
)