package viacheslav.chugunov.appalet.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import viacheslav.chugunov.core.util.Serializer
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
class CoreModule {

    @Provides
    fun coroutineContext(): CoroutineContext =
        Dispatchers.IO

    @Provides
    fun serializer(): Serializer =
        Serializer.Default()
}