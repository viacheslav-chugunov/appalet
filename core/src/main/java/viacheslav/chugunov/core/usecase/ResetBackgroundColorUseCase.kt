package viacheslav.chugunov.core.usecase

import viacheslav.chugunov.core.repository.PreferredColorsRepository

interface ResetBackgroundColorUseCase {
    suspend fun invoke(isLight: Boolean)


    class Default(
        private val preferredColorsRepository: PreferredColorsRepository
    ) : ResetBackgroundColorUseCase {
        override suspend fun invoke(isLight: Boolean) {
            preferredColorsRepository.resetBackground(isLight)
        }
    }
}