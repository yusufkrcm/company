package com.company.employee.config

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

    @Bean
    fun dataSource(): DataSource {
        createDatabaseIfNotExists()
        val dataSource = HikariDataSource()
        dataSource.jdbcUrl = databaseUrl + databaseName
        dataSource.username = databaseUsername
        dataSource.password = databasePassword
        dataSource.driverClassName = databaseDriver
        return dataSource
    }

    fun createDatabaseIfNotExists() {
        val dataSource = HikariDataSource()
        dataSource.jdbcUrl = databaseUrl
        dataSource.username = databaseUsername
        dataSource.password = databasePassword
        dataSource.driverClassName = databaseDriver

        val jdbcTemplate = JdbcTemplate(dataSource)

        val result = jdbcTemplate.query("SELECT * FROM pg_database WHERE datname = '$databaseName'")
        { rs, _ ->
            rs.getString("datname")
        }.firstOrNull()

        if (result == null) {
            jdbcTemplate.execute("CREATE DATABASE $databaseName")
        }
    }

}

