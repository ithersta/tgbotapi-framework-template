package domain.common

data class Pagination(
    val offset: Int,
    val limit: Int,
)

data class Paginated<T>(
    val allItemsCount: Int,
    val items: List<T>,
)
