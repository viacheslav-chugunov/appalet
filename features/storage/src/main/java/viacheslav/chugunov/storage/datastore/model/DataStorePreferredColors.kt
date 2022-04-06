package viacheslav.chugunov.storage.datastore.model

import viacheslav.chugunov.core.model.Coloring
import viacheslav.chugunov.core.model.PreferredColors

class DataStorePreferredColors(
    lightBackground: DataStoreColoring,
    darkBackground: DataStoreColoring
) : PreferredColors.Default(lightBackground, darkBackground)