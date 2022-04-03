package viacheslav.chugunov.appalet.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import viacheslav.chugunov.core.util.Serializer

@Module
@InstallIn(SingletonComponent::class)
class CoreModule {

    @Provides
    fun serializer(): Serializer =
        Serializer.Default()
}