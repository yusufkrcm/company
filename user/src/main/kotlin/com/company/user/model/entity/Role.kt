package com.company.user.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "roles")
data class Role (
    @Column(unique = true)
    val name: String,
    @OneToMany(mappedBy = "role")
    val users: List<User>
) : BaseEntity()