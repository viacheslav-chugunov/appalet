package viacheslav.chugunov.appalet.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import viacheslav.chugunov.appalet.ui.screen.collection.CollectionModel
import viacheslav.chugunov.appalet.ui.screen.colors.ColorsModel
import viacheslav.chugunov.appalet.ui.screen.main.MainModel
import viacheslav.chugunov.appalet.ui.screen.preview.dialog.DialogPreviewModel
import viacheslav.chugunov.appalet.ui.screen.preview.input.InputPreviewModel
import viacheslav.chugunov.appalet.ui.screen.preview.list.ListPreviewModel
import viacheslav.chugunov.appalet.ui.screen.settings.SettingsModel
import viacheslav.chugunov.core.util.Serializer
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {

    @Provides
    fun coroutineContext(): CoroutineContext = Dispatchers.IO

    @Provides
    fun collectionModel(): CollectionModel = CollectionModel()

    @Provides
    fun colorsModel(): ColorsModel = ColorsModel()

    @Provides
    fun mainModel(): MainModel = MainModel()

    @Provides
    fun dialogPreviewModel(): DialogPreviewModel = DialogPreviewModel()

    @Provides
    fun inputPreviewModel(): InputPreviewModel = InputPreviewModel()

    @Provides
    fun listPreviewModel(): ListPreviewModel = ListPreviewModel()

    @Provides
    fun settingsModel(): SettingsModel = SettingsModel()

}