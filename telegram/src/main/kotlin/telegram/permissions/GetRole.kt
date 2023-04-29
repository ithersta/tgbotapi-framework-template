package telegram.permissions

import com.ithersta.tgbotapi.core.GetRole
import org.koin.core.annotation.Single

@Single
fun getRole() = GetRole { SampleRole }
