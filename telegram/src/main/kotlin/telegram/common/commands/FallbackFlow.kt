package telegram.common.commands

import com.ithersta.tgbotapi.autoconfigure.DialogueFlow
import com.ithersta.tgbotapi.basetypes.MessageState
import com.ithersta.tgbotapi.basetypes.Role
import dev.inmo.tgbotapi.extensions.api.send.send
import org.koin.core.annotation.Single
import telegram.common.Priorities
import telegram.common.resources.Strings

@Single
class FallbackFlow : DialogueFlow() {
    val fallback = inState<Role, MessageState>(priority = Priorities.Fallback) {
        on<Any> {
            send(chat, Strings.Fallback)
        }
    }
}
