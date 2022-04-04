package viacheslav.chugunov.storage.datastore.model

import viacheslav.chugunov.core.model.Coloring
import viacheslav.chugunov.core.model.PreferredColors

class DataStorePreferredColors(
    override val lightBackground: DataStoreColoring,
    override val darkBackground: DataStoreColoring
) : PreferredColors