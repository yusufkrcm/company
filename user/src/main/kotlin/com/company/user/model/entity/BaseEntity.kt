package com.company.user.model.entity

import com.company.user.model.entity.listener.BaseEntityListener
import jakarta.persistence.EntityListeners
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.Version
import org.hibernate.annotations.UuidGenerator
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

@MappedSuperclass
@EntityListeners(BaseEntityListener::class)
abstract class BaseEntity : Serializable {
    @Id
    @UuidGenerator
    var id: UUID? = null
    var createdDate: LocalDateTime? = null
    var updatedDate: LocalDateTime? = null
    var createdId: UUID? = null
    var updatedId: UUID? = null
    var deleted: Boolean? = null
    @Version
    var version: Long? = null
}
