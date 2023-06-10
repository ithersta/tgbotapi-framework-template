package telegram.common

import com.ithersta.tgbotapi.basetypes.Role
import com.ithersta.tgbotapi.builders.command
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Single
@Named("startCommand")
fun startCommand() = command<Role>("start", description = null) {
    updateCommands()
}
