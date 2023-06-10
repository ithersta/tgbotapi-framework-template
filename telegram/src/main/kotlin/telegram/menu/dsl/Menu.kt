package telegram.menu.dsl

import com.ithersta.tgbotapi.basetypes.MessageState
import com.ithersta.tgbotapi.basetypes.Role
import com.ithersta.tgbotapi.core.HandlerContext
import com.ithersta.tgbotapi.core.StateAccessor
import dev.inmo.tgbotapi.types.MessageId

inline fun <S : MessageState, SA : StateAccessor<S>, R : Role, M : MessageId?> HandlerContext<S, SA, R, M>.menu(
    block: MenuBuilder<S, SA, R, M>.() -> Unit,
) = MenuBuilder(this).apply(block).items
