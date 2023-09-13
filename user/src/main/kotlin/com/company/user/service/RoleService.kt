package com.company.user.service

import com.company.user.model.entity.Role

interface RoleService {
    fun findRoleByName(name: String): Role
}