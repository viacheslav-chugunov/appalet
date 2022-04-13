package viacheslav.chugunov.core.usecase

import viacheslav.chugunov.core.repository.PreferredColorsRepository
import viacheslav.chugunov.core.repository.ThemeRepository

interface ChangeBackgroundColorAsRandomUseCase {
    suspend fun invoke(isLight: Boolean)


    class Default(
        private val themeRepository: ThemeRepository,
        private val preferredColorsRepository: PreferredColorsRepository
    ) : ChangeBackgroundColorAsRandomUseCase {
        override suspend fun invoke(isLight: Boolean) {
            val color = themeRepository.getRandomColor()
            preferredColorsRepository.setBackground(isLight, color)
        }
    }
}