package domain.common

interface Transaction {
    suspend operator fun <R> invoke(action: suspend Transaction.() -> R): R
}
