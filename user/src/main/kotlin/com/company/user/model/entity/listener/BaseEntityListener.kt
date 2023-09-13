package com.company.user.model.entity.listener

import com.company.user.model.entity.BaseEntity
import com.company.user.util.JwtUtil
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class BaseEntityListener  {

    @Autowired
    private lateinit var jwtUtil: JwtUtil

    @PrePersist
    fun prePersist(entity: BaseEntity) {
        val now = LocalDateTime.now()
        entity.createdDate = now
        entity.updatedDate = now
        entity.deleted = false

        val token = jwtUtil.getJwtFromRequest()
        if (!token.isNullOrEmpty()){
            entity.createdId = UUID.fromString(jwtUtil.getSubject(token))
            entity.updatedId = UUID.fromString(jwtUtil.getSubject(token))
        }
    }

    @PreUpdate
    fun preUpdate(entity: BaseEntity) {
        entity.updatedDate = LocalDateTime.now()

        val token = jwtUtil.getJwtFromRequest()
        if (!token.isNullOrEmpty())
            entity.updatedId = UUID.fromString(jwtUtil.getSubject(token))
    }

}