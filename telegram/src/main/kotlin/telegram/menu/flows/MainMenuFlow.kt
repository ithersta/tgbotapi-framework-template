package telegram.menu.flows

import com.ithersta.tgbotapi.autoconfigure.DialogueFlow
import com.ithersta.tgbotapi.basetypes.MessageState
import com.ithersta.tgbotapi.basetypes.Role
import com.ithersta.tgbotapi.builders.inState
import dev.inmo.tgbotapi.extensions.api.send.send
import dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard
import dev.inmo.tgbotapi.extensions.utils.types.buttons.simpleButton
import dev.inmo.tgbotapi.types.message.content.TextMessage
import dev.inmo.tgbotapi.utils.row
import kotlinx.serialization.Serializable
import org.koin.core.annotation.Single
import telegram.common.resources.Strings
import telegram.menu.dsl.MenuItem
import telegram.menu.mainMenu

@Single
class MainMenuFlow : DialogueFlow {
    @Serializable
    data class MenuState(
        val text: String,
        val returnTo: MessageState,
    ) : MessageState

    val empty = inMenuState<MessageState.Empty> { null }
    val menu = inMenuState<MenuState> { text }

    private inline fun <reified S : MessageState> inMenuState(
        crossinline getText: S.() -> String?,
    ) = inState<Role, S> {
        onNewOrEdit {
            val menu = mainMenu()
            val submenu = state.snapshot.getText()
                ?.let { menu.find(it) as? MenuItem.Submenu }
            send(
                chat = chat,
                text = submenu?.message ?: Strings.Menu,
                replyMarkup = replyKeyboard(resizeKeyboard = true) {
                    (submenu?.items ?: menu).forEach { row { simpleButton(it.text) } }
                    if (submenu != null) {
                        row { simpleButton(Strings.Back) }
                    }
                },
            )
        }
        on<TextMessage> { message ->
            mainMenu()
                .find(message.content.text)
                ?.let { item ->
                    when (item) {
                        is MenuItem.Button -> state.new { item.targetState }
                        is MenuItem.Submenu -> state.new { MenuState(item.text, this) }
                    }
                } ?: return@on fallthrough()
        }
    }

    val back = inState<Role, MenuState> {
        on<TextMessage> { message ->
            if (message.content.text != Strings.Back) {
                return@on fallthrough()
            }
            state.new { returnTo }
        }
    }

    private fun List<MenuItem>.find(text: String): MenuItem? =
        firstNotNullOfOrNull { it.find(text) }

    private fun MenuItem.find(text: String): MenuItem? = when {
        this.text == text -> this
        this is MenuItem.Submenu -> items.find(text)
        else -> null
    }
}
