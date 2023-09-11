package com.company.user.model.entity

import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.io.Serializable
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null

    @CreationTimestamp
    val createdDate: LocalDateTime? = null

    @UpdateTimestamp
    val updatedDate: LocalDateTime? = null
}
