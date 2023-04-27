package sample

import com.ithersta.tgbotapi.basetypes.User
import com.ithersta.tgbotapi.core.GetUser
import org.koin.core.annotation.Single

object AnyUser : User

@Single
fun getUser() = GetUser { AnyUser }
