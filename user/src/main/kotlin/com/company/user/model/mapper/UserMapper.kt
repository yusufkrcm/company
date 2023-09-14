package com.company.user.model.mapper

import com.company.user.model.entity.Role
import com.company.user.model.entity.User
import com.company.user.model.exception.MapperException
import org.mapstruct.Mapper

@Mapper(componentModel = "spring", unexpectedValueMappingException = MapperException::class)
abstract class UserMapper {
    abstract fun toEntity(role: Role): User
}
