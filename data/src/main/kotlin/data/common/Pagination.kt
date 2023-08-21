package data.common

import domain.common.Paginated
import domain.common.Pagination
import org.jetbrains.exposed.sql.SizedIterable

fun <T, R> SizedIterable<T>.paginate(with: Pagination, map: (T) -> R) = Paginated(
    allItemsCount = copy().count().toInt(),
    items = limit(n = with.limit, offset = with.offset.toLong()).map(map),
)
