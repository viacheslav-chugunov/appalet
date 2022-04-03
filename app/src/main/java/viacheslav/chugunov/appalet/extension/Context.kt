package viacheslav.chugunov.appalet.extension

import android.content.Context
import viacheslav.chugunov.core.model.Language
import java.util.*

fun Context.setLanguage(language: Language) {
    val locale = language.locale
    Locale.setDefault(locale)
    val configuration = resources.configuration
    configuration.locale = locale
    resources.updateConfiguration(configuration, resources.displayMetrics)
}