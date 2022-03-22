package com.soon83.workmanagement.domain

import com.soon83.workmanagement.enumcode.Gender
import javax.persistence.*

@Entity
@Table(name = "app_user")
class User(

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(length = 15)
    var name: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(length = 31)
    val gender: Gender? = null,

    val age: Int? = null,
)