package viacheslav.chugunov.storage.datastore.model

import viacheslav.chugunov.core.model.ColorDescription
import viacheslav.chugunov.core.model.Coloring

class DataStoreColoring(
    override val color: ColorDescription.Default,
    override val onColor: ColorDescription.Default
) : Coloring