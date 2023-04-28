import com.ithersta.tgbotapi.autoconfigure.autoconfigure
import data.DataModule
import telegram.TelegramModule
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

suspend fun main(): Unit = startKoin {
    modules(TelegramModule().module, DataModule().module)
}.autoconfigure()
