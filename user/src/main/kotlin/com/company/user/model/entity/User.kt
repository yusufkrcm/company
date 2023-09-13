package com.company.user.model.entity

import jakarta.persistence.*

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
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    val role: Role
) : BaseEntity()

