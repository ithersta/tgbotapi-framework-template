package telegram.common

import com.ithersta.tgbotapi.core.HandlerContext
import com.ithersta.tgbotapi.core.StateAccessor
import com.ithersta.tgbotapi.init.plugins.WithPagination
import com.ithersta.tgbotapi.init.plugins.limit
import com.ithersta.tgbotapi.init.plugins.offset
import domain.common.Pagination

val <S : WithPagination<S>> HandlerContext<S, out StateAccessor<S>, *, *>.pagination
    get() = Pagination(offset = offset, limit = limit)
