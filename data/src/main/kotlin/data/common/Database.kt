package data.common

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.annotation.Single
import org.postgresql.ds.PGSimpleDataSource
import javax.sql.DataSource

@Single(binds = [DataSource::class])
fun dataSource() = PGSimpleDataSource().apply {
    val env = System.getenv()
    databaseName = env["DATABASE_NAME"] ?: "tgbotapi-framework-template"
    env["DATABASE_SERVER"]?.let { serverNames = arrayOf(it) }
    env["DATABASE_PORT"]?.let { portNumbers = intArrayOf(it.toInt()) }
    env["DATABASE_USER"]?.let { user = it }
    env["DATABASE_PASSWORD"]?.let { password = it }
}

@Single(createdAtStart = true)
fun database(dataSource: DataSource) = Database.connect(dataSource).also {
    transaction(it) {
        SchemaUtils.createMissingTablesAndColumns()
    }
}
