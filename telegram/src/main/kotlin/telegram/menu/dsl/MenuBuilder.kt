package telegram.menu.dsl

import arrow.core.toNonEmptyListOrNull
import com.ithersta.tgbotapi.basetypes.MessageState
import com.ithersta.tgbotapi.basetypes.Role
import com.ithersta.tgbotapi.core.HandlerContext
import com.ithersta.tgbotapi.core.StateAccessor
import dev.inmo.tgbotapi.types.MessageId

@MenuBuilderDsl
class MenuBuilder<S : MessageState, SA : StateAccessor<S>, R : Role, M : MessageId?>(
    context: HandlerContext<S, SA, R, M>,
) : HandlerContext<S, SA, R, M> by context {
    private val _items = mutableListOf<MenuItem>()
    val items: List<MenuItem> = _items

    fun button(text: String, targetState: MessageState, condition: () -> Boolean = { true }) {
        if (condition()) {
            _items.add(MenuItem.Button(text, targetState))
        }
    }

    fun submenu(text: String, message: String, block: MenuBuilder<S, SA, R, M>.() -> Unit) {
        MenuBuilder(this).apply(block).items.toNonEmptyListOrNull()?.let {
            _items.add(MenuItem.Submenu(text, message, it))
        }
    }
}
