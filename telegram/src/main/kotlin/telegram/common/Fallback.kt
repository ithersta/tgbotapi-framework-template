package telegram.common

import com.ithersta.tgbotapi.basetypes.MessageState
import com.ithersta.tgbotapi.basetypes.Role
import com.ithersta.tgbotapi.builders.inState
import dev.inmo.tgbotapi.extensions.api.send.send
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import telegram.common.resources.Strings

@Single
@Named("fallback")
fun fallback() = inState<Role, MessageState>(priority = Priorities.Fallback) {
    on<Any> {
        send(chat, Strings.Fallback)
    }
}
