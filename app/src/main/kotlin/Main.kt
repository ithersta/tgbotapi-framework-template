import com.ithersta.tgbotapi.autoconfigure.autoconfigure
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module
import sample.DomainModule
import sample.TelegramModule

suspend fun main(): Unit = startKoin {
    modules(TelegramModule().module, DomainModule().module)
}.autoconfigure()
