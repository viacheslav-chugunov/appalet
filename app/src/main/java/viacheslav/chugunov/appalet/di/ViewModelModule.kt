package viacheslav.chugunov.appalet.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import viacheslav.chugunov.core.util.Serializer
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    fun coroutineContext(): CoroutineContext =
        Dispatchers.IO
    
}