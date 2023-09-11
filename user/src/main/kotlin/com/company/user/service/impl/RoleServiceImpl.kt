package com.company.user.service.impl

import com.company.user.model.entity.Role
import com.company.user.repository.RoleRepository
import com.company.user.service.RoleService
import org.springframework.stereotype.Service

@Service
class RoleServiceImpl(private val roleRepository: RoleRepository) : RoleService {
    override fun findRoleByName(name: String): Role =
        roleRepository.findByName(name) ?: throw Exception("Role not found")
}