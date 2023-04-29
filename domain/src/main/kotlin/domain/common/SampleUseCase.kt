package domain.common

import org.koin.core.annotation.Single

@Single
class SampleUseCase(
    private val transaction: Transaction,
) {
    suspend operator fun invoke() = transaction {
    }
}
