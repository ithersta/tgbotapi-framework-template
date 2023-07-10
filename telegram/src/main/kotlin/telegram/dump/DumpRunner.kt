package telegram.dump

import arrow.core.raise.either
import arrow.resilience.Schedule
import com.ithersta.tgbotapi.core.runner.statefulRunner
import dev.inmo.micro_utils.coroutines.launchSafely
import dev.inmo.tgbotapi.extensions.api.send.media.sendDocument
import dev.inmo.tgbotapi.extensions.api.send.send
import dev.inmo.tgbotapi.requests.abstracts.asMultipartFile
import dev.inmo.tgbotapi.types.toChatId
import io.github.oshai.kotlinlogging.KotlinLogging
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import java.time.Instant
import kotlin.time.Duration.Companion.hours

private val dumpEndpoint = System.getenv()["DUMP_ENDPOINT"]
private val dumpChatId = System.getenv()["DUMP_CHAT_ID"]?.toLong()?.toChatId()
private val dumpInterval = 8.hours
private val logger = KotlinLogging.logger { }

@Single
@Named("dumpRunner")
fun dumpRunner() = statefulRunner {
    if (dumpChatId == null) {
        logger.info("DUMP_CHAT_ID is not set")
        return@statefulRunner
    }
    launchSafely(
        onException = { exception ->
            logger.error("Exception during dump", exception)
        },
    ) {
        if (dumpEndpoint == null) {
            send(dumpChatId, "DUMP_ENDPOINT is not set")
            return@launchSafely
        }
        val api = DumpApi(dumpEndpoint)
        Schedule.spaced<Unit>(dumpInterval).repeat {
            api.get().onRight {
                sendDocument(dumpChatId, it.asMultipartFile("dump-${Instant.now()}.sql"))
            }.onLeft {
                send(dumpChatId, it)
            }
        }
    }
}

private class DumpApi(
    private val url: String,
    private val client: HttpClient = HttpClient { },
) {
    suspend fun get() = either {
        val response = client.get(url)
        if (response.status.isSuccess().not()) raise(response.bodyAsText())
        response.bodyAsChannel()
    }
}
