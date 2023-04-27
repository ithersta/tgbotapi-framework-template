package common

interface Transaction {
    suspend operator fun <R> invoke(action: () -> R): R
}
