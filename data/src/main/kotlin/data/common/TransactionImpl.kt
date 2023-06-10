package data.common

import domain.common.Transaction
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.koin.core.annotation.Single

@Single
class TransactionImpl(private val database: Database) : Transaction {
    override suspend fun <R> invoke(action: suspend Transaction.() -> R): R {
        return newSuspendedTransaction(db = database) {
            addLogger(StdOutSqlLogger)
            action()
        }
    }
}
