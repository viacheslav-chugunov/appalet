package viacheslav.chugunov.core.util

import com.google.gson.Gson

interface Serializer {
    fun <T> toString(model: T): String
    fun <T> fromString(string: String, modelClass: Class<T>): T
    fun <T> fromStringOrDefault(string: String?, modelClass: Class<out T>, default: T): T



    class Default : Serializer {
        private val gson = Gson()

        override fun <T> toString(model: T): String =
            gson.toJson(model)

        override fun <T> fromString(string: String, modelClass: Class<T>): T =
            gson.fromJson(string, modelClass)

        override fun <T> fromStringOrDefault(string: String?, modelClass: Class<out T>, default: T): T {
            if (string.isNullOrBlank() && string == "{}")
                return default
            return gson.fromJson(string, modelClass)
        }
    }
}