package com.company.user.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource


@Configuration
class DatabaseInitializer {

    @Value("\${database.url}")
    private lateinit var databaseUrl: String

    @Value("\${database.name}")
    private lateinit var databaseName: String

    @Value("\${database.username}")
    private lateinit var databaseUsername: String

    @Value("\${database.password}")
    private lateinit var databasePassword: String

    @Value("\${database.driverClassName}")
    private lateinit var databaseDriver: String

    @Value("\${database.createIfNotExist}")
    private var createIfNotExist: Boolean = false


    @Bean
    fun dataSource(): DataSource {
        if (createIfNotExist)
            createDatabaseIfNotExists()

        return getDataSource(databaseUrl + databaseName)
    }

    fun createDatabaseIfNotExists() {

        val jdbcTemplate = JdbcTemplate(getDataSource(databaseUrl))

        val result = jdbcTemplate.query("SELECT * FROM pg_database WHERE datname = '$databaseName'")
        { rs, _ ->
            rs.getString("datname")
        }.firstOrNull()

        if (result == null) {
            jdbcTemplate.execute("CREATE DATABASE $databaseName")
        }
    }

    fun getDataSource(url: String): DataSource {
        return HikariDataSource().apply {
            jdbcUrl = url
            username = databaseUsername
            password = databasePassword
            driverClassName = databaseDriver
        }
    }

}

