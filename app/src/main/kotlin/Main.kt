import com.ithersta.tgbotapi.autoconfigure.autoconfigureBot
import com.ithersta.tgbotapi.autoconfigure.generatedSerializersModule
import com.ithersta.tgbotapi.init.plugins.DumpRunner
import data.DataModule
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module
import telegram.TelegramModule

suspend fun main(): Unit = startKoin {
    modules(TelegramModule().module, DataModule().module)
}.autoconfigureBot {
    serializersModule(generatedSerializersModule())
    install(DumpRunner)
}
