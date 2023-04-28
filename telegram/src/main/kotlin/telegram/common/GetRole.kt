package telegram.common

import com.ithersta.tgbotapi.basetypes.Role
import com.ithersta.tgbotapi.core.GetRole
import org.koin.core.annotation.Single

object SampleRole : Role

@Single
fun getRole() = GetRole { SampleRole }
