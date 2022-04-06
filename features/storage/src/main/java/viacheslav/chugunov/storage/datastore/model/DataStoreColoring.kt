package viacheslav.chugunov.storage.datastore.model

import viacheslav.chugunov.core.model.ColorDescription
import viacheslav.chugunov.core.model.Coloring

class DataStoreColoring(
    color: ColorDescription.Default,
    onColor: ColorDescription.Default
) : Coloring.Default(color, onColor)