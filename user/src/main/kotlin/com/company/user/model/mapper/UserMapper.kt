package com.company.user.model.mapper

import com.company.user.model.entity.User
import com.company.user.model.exception.MapperException
import com.company.user.model.request.RegisterRequest
import org.mapstruct.Mapper
import org.springframework.stereotype.Component

@Component
@Mapper(componentModel = "spring", unexpectedValueMappingException = MapperException::class)
abstract class UserMapper {
    abstract fun toEntity(request: RegisterRequest): User
}
