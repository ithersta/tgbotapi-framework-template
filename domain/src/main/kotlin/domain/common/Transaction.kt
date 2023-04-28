package domain.common

interface Transaction {
    suspend operator fun <R> invoke(action: Transaction.() -> R): R
}
