package telegram.common.resources

object Strings {
    object Cancel {
        const val Description = "отменить"
        const val OnSuccess = "Операция отменена"
    }

    const val Fallback = "Нет такой команды"
    const val Menu = "Меню"
    const val Back = "◀️Назад"
    const val Delete = "Удалить"

    fun maxLengthExceeded(maxLength: Int) =
        "Превышен лимит символов ($maxLength). Сократите сообщение и попробуйте ещё раз."
}
