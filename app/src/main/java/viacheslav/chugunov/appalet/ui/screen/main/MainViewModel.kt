package viacheslav.chugunov.appalet.ui.screen.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import viacheslav.chugunov.appalet.R
import viacheslav.chugunov.core.model.Language
import viacheslav.chugunov.core.model.PreferredColors
import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.core.usecase.*
import viacheslav.chugunov.core.util.BaseViewModel
import viacheslav.chugunov.core.util.Screen
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRandomThemeUseCase: GetRandomThemeUseCase,
    private val getSettingsUseCase: GetSettingsUseCase,
    private val isThemeInCollectionUseCase: IsThemeInCollectionUseCase,
    private val addThemeToCollectionUseCase: AddThemeToCollectionUseCase,
    private val removeCollectedThemeUseCase: RemoveCollectedThemeUseCase,
    model: MainModel,
    coroutineContext: CoroutineContext
) : BaseViewModel<MainModel>(model, coroutineContext) {

    init {
        subscribeOnObservable()
    }

    fun updateModel(
        theme: Theme = model.theme,
        language: Language = model.language,
        preferredColors: PreferredColors = model.preferredColors,
        modeDay: Boolean = model.modeDay,
        preview: Screen.Preview = model.preview,
        currentScreen: Screen = model.currentScreen,
        closeAppOnBackPress: Boolean = model.closeAppOnBackPress,
        inFavourites: Boolean = model.inFavourites,
        loading: Boolean = model.loading,
        favouritesVisible: Boolean = model.favouritesVisible,
        changeTypeLetterRes: Int = model.changeTypeLetterRes
    ) {
        model = MainModel(
            theme,
            language,
            preferredColors,
            modeDay,
            preview,
            currentScreen,
            closeAppOnBackPress,
            inFavourites,
            loading,
            favouritesVisible,
            changeTypeLetterRes
        )
        modelMutableFlow.value = model
    }

    fun subscribeOnObservable(): Job =
        viewModelScope.launch(coroutineContext) {
            getSettingsUseCase.invoke().collect { settingsConfig ->
                val language = settingsConfig.language
                val preferredColors = settingsConfig.preferredColors
                val modeLight = model.modeDay
                val currentTheme = model.theme
                val newTheme = Theme.Adaptive(modeLight, currentTheme, preferredColors)
                val inFavourites = isThemeInCollectionUseCase.invoke(newTheme)
                val isLoading = model.loading
                if (isLoading) changeThemeAsRandom()
                updateModel(
                    language = language,
                    preferredColors = preferredColors,
                    theme = newTheme,
                    inFavourites = inFavourites,
                    loading = false
                )
            }
        }

    fun updateThemeTypeChange() {
        val type = when (model.changeTypeLetterRes) {
            R.string.theme_change_type_all -> R.string.theme_change_type_primary
            R.string.theme_change_type_primary -> R.string.theme_change_type_secondary
            R.string.theme_change_type_secondary -> R.string.theme_change_type_all
            else -> throw IllegalStateException()
        }
        updateModel(changeTypeLetterRes = type)
    }

    fun changeThemeAsRandom(): Job =
        viewModelScope.launch(coroutineContext) {
            val isLight = model.modeDay
            val preferredColors = model.preferredColors
            val theme = getRandomThemeUseCase.invoke(isLight, preferredColors)
            val newPrimarySet = theme.colorsPrimary
            val newSecondarySet = theme.colorsSecondary
            val curPrimarySet = model.theme.colorsPrimary
            val curSecondarySet = model.theme.colorsSecondary
            val newTheme = when (model.changeTypeLetterRes) {
                R.string.theme_change_type_all -> Theme.Adaptive(
                    isLight,
                    newPrimarySet,
                    newSecondarySet,
                    preferredColors
                )
                R.string.theme_change_type_primary -> Theme.Adaptive(
                    isLight,
                    newPrimarySet,
                    curSecondarySet,
                    preferredColors
                )
                R.string.theme_change_type_secondary -> Theme.Adaptive(
                    isLight,
                    curPrimarySet,
                    newSecondarySet,
                    preferredColors
                )
                else -> throw IllegalStateException()
            }
            val inFavourites = isThemeInCollectionUseCase.invoke(theme)
            updateModel(theme = newTheme, inFavourites = inFavourites)
        }

    fun changeTheme(theme: Theme): Job =
        viewModelScope.launch(coroutineContext) {
            val inFavourites = isThemeInCollectionUseCase.invoke(theme)
            updateModel(theme = theme, inFavourites = inFavourites)
        }

    fun changeDayMode(): Job =
        viewModelScope.launch(coroutineContext) {
            val isLight = model.modeDay
            val theme = model.theme
            val preferredColors = model.preferredColors
            val newTheme = Theme.Adaptive(!isLight, theme, preferredColors)
            val inFavourites = isThemeInCollectionUseCase.invoke(newTheme)
            updateModel(
                theme = newTheme,
                modeDay = !isLight,
                inFavourites = inFavourites
            )
        }

    fun onThemeRemoved(theme: Theme): Job =
        viewModelScope.launch(coroutineContext) {
            val currentTheme = model.theme
            if (currentTheme == theme) {
                updateModel(favouritesVisible = false)
                delay(200)
            }
            val inFavourites = isThemeInCollectionUseCase.invoke(currentTheme)
            updateModel(inFavourites = inFavourites)
            updateModel(favouritesVisible = true)
        }

    fun changeFavourites(): Job =
        viewModelScope.launch(coroutineContext) {
            val theme = model.theme
            val nowInFavourites = model.inFavourites
            if (nowInFavourites) {
                removeCollectedThemeUseCase.invoke(theme)
            } else {
                addThemeToCollectionUseCase.invoke(theme)
            }
            updateModel(favouritesVisible = false)
            delay(200)
            val inFavourites = isThemeInCollectionUseCase.invoke(theme)
            updateModel(inFavourites = inFavourites)
            updateModel(favouritesVisible = true)
        }

    fun updatePreviewToPrevious() {
        val preview = model.preview.previous
        updateModel(preview = preview)
    }

    fun updatePreviewToNext() {
        val preview = model.preview.next
        updateModel(preview = preview)
    }
}