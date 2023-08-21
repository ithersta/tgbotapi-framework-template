package telegram.common.commands

import com.ithersta.tgbotapi.autoconfigure.DialogueFlow
import com.ithersta.tgbotapi.basetypes.MessageState
import com.ithersta.tgbotapi.basetypes.Role
import dev.inmo.tgbotapi.extensions.api.send.sendTextMessage
import org.koin.core.annotation.Single
import telegram.common.resources.Strings

@Single
class CancelCommandFlow : DialogueFlow() {
    val command = command<Role>("cancel", description = Strings.Cancel.Description) {
        if (state.snapshot != MessageState.Empty) {
            sendTextMessage(it.chat, Strings.Cancel.OnSuccess)
        }
        state.new { MessageState.Empty }
    }
}
