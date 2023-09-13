package com.company.user.repository

import com.company.user.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User,String> {
    fun findByUsername(username: String): Optional<User>
}