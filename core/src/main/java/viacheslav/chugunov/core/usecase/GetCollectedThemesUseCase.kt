package viacheslav.chugunov.core.usecase

import kotlinx.coroutines.flow.Flow
import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.core.repository.PreferredThemesRepository

interface GetCollectedThemesUseCase {
    fun invoke(): Flow<List<Theme>>


    class Default(
        private val preferredThemesRepository: PreferredThemesRepository
    ) : GetCollectedThemesUseCase {
        override fun invoke(): Flow<List<Theme>> {
            return preferredThemesRepository.getThemesFlow()
        }
    }
}