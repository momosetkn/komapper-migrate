package momosetkn.komapper

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import javax.sql.DataSource

internal fun createHikariDataSource(): DataSource {
    val hikariConfig = HikariConfig().apply {
        jdbcUrl = "jdbc:tc:postgis:12-3.4:///test?TC_DAEMON=true"
        username = "test"
        password = "test"
        maximumPoolSize = 1
        isAutoCommit = false
    }
    val hikariDataSource = HikariDataSource(hikariConfig)
    return hikariDataSource
}
