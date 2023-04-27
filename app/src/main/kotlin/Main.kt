import com.ithersta.tgbotapi.autoconfigure.autoconfigure
import common.DataModule
import common.DomainModule
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module
import sample.TelegramModule

suspend fun main(): Unit = startKoin {
    modules(DomainModule().module, TelegramModule().module, DataModule().module)
}.autoconfigure()
