package domain.common

import arrow.core.raise.either
import arrow.core.raise.ensure

data class MaxLengthExceeded(val maxLength: Int)

open class LimitedLengthStringType<T : Any>(
    val maxLength: Int,
    private val construct: (String) -> T,
) {
    fun of(value: String) = either {
        val trimmedValue = value.trim()
        ensure(trimmedValue.length <= maxLength) { MaxLengthExceeded(maxLength) }
        construct(trimmedValue)
    }

    fun ofTruncated(value: String) = construct(value.trim().take(maxLength))
}
