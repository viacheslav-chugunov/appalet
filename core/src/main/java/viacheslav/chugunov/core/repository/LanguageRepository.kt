package viacheslav.chugunov.core.repository

import kotlinx.coroutines.flow.Flow
import viacheslav.chugunov.core.model.Language

interface LanguageRepository {
    suspend fun getLanguage(): Language
    fun getLanguageFlow(): Flow<Language>
    suspend fun setLanguage(language: Language)
}