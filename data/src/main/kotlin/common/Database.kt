package common

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.annotation.Single

@Single(createdAtStart = true)
fun database() = Database.connect("jdbc:postgresql:database", user = "ithersta").also {
    transaction(it) {
        SchemaUtils.createMissingTablesAndColumns()
    }
}
