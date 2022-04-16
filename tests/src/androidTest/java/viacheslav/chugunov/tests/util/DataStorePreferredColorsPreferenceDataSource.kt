package viacheslav.chugunov.tests.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import viacheslav.chugunov.core.datasource.PreferenceDataSource
import viacheslav.chugunov.storage.datastore.model.DataStorePreferredColors

class DataStorePreferredColorsPreferenceDataSource(
    var colors: DataStorePreferredColors?
) : PreferenceDataSource<DataStorePreferredColors> {

    override fun getFlow(): Flow<DataStorePreferredColors?> {
        return flowOf(colors)
    }

    override suspend fun set(data: DataStorePreferredColors) {
        colors = data
    }

    override suspend fun clear() {
        colors = null
    }

}