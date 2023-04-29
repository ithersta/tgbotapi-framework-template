package telegram.common

import com.ithersta.tgbotapi.basetypes.MessageState
import com.ithersta.tgbotapi.basetypes.Role
import com.ithersta.tgbotapi.builders.command
import dev.inmo.tgbotapi.extensions.api.send.sendTextMessage
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import telegram.common.resources.Strings

@Single
@Named("cancelCommand")
fun cancelCommand() = command<Role>("cancel", description = Strings.Cancel.Description) {
    if (state.snapshot != MessageState.Empty) {
        sendTextMessage(it.chat, Strings.Cancel.OnSuccess)
    }
    state.new { MessageState.Empty }
}
