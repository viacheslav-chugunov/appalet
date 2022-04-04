package viacheslav.chugunov.core.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import viacheslav.chugunov.core.model.ColorDescription
import viacheslav.chugunov.core.model.Coloring
import viacheslav.chugunov.core.model.PreferredColors

interface PreferredColorsRepository {
    fun getColorsFlow(): Flow<PreferredColors>
    suspend fun getColors(): PreferredColors = getColorsFlow().first()
    suspend fun setLightBackground(color: Coloring)
    suspend fun setDarkBackground(color: Coloring)
    suspend fun resetLightBackground()
    suspend fun resetDarkBackground()
}