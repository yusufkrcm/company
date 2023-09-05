package com.company.employee.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class BaseEntity {
    @Id
    var id: Long = TODO("initialize me")
}