package telegram.menu.dsl

import arrow.core.NonEmptyList
import com.ithersta.tgbotapi.basetypes.MessageState

sealed interface MenuItem {
    val text: String

    data class Submenu(override val text: String, val message: String, val items: NonEmptyList<MenuItem>) : MenuItem
    data class Button(override val text: String, val targetState: MessageState) : MenuItem
}
