package data.common

import domain.common.Transaction
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.koin.core.annotation.Single

@Single
class TransactionImpl(private val database: Database) : Transaction {
    override suspend fun <R> invoke(action: Transaction.() -> R): R {
        return newSuspendedTransaction(db = database) { action() }
    }
}
