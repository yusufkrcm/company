package com.company.user.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "users")
data class User(
    @Column(unique = true)
    val username: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    @Column(unique = true)
    val email: String,
    @Column(unique = true)
    val phoneNumber: String,
    @ManyToOne
    val role: Role? = null
) : BaseEntity()

