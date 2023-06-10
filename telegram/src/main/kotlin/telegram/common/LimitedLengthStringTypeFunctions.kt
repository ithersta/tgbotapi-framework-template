package telegram.common

import arrow.core.Either
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.send.send
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.content.TextMessage
import domain.common.LimitedLengthStringType
import domain.common.MaxLengthExceeded
import telegram.common.resources.Strings

context(TelegramBot)
suspend fun <T : Any> LimitedLengthStringType<T>.fromMessage(
    message: TextMessage,
    block: suspend (T) -> Unit,
) = of(message.content.text).handleMaxLengthExceededOr(message.chat, block)

context(TelegramBot)
suspend fun <T : Any> Either<MaxLengthExceeded, T>.handleMaxLengthExceededOr(
    chat: Chat,
    block: suspend (T) -> Unit,
) {
    when (this) {
        is Either.Right -> block(value)
        is Either.Left -> send(chat, Strings.maxLengthExceeded(value.maxLength))
    }
}
