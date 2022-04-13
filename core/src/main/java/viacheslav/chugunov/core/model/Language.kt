package viacheslav.chugunov.core.model

import viacheslav.chugunov.core.R
import java.util.*

interface Language {
    val locale: Locale
    val flagIconId: Int
    val nameId: Int

    val isEnglish get() = locale.language == "en"
    val isRussian get() = locale.language == "ru"
    val isUkrainian get() = locale.language == "uk"


    open class Default(
        override val locale: Locale,
        override val flagIconId: Int,
        override val nameId: Int
    ) : Language


    object English : Default(Locale("en"), R.drawable.flag_us, R.string.english)
    object Russian : Default(Locale("ru"), R.drawable.flag_ru, R.string.russian)
    object Ukrainian : Default(Locale("uk"), R.drawable.flag_ua, R.string.ukrainian)
}