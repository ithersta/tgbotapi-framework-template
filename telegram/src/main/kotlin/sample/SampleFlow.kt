package sample

import com.ithersta.tgbotapi.basetypes.User
import com.ithersta.tgbotapi.builders.command
import dev.inmo.tgbotapi.extensions.api.send.sendTextMessage
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Single
@Named("startCommand")
fun startCommand() = command<User>("start", description = "начать") {
    sendTextMessage(it.chat, "Привет!")
}
