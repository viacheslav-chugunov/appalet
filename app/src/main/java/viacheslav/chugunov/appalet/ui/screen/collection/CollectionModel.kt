package viacheslav.chugunov.appalet.ui.screen.collection

import viacheslav.chugunov.core.model.Theme

data class CollectionModel(
    val themes: List<Theme> = emptyList(),
    val visibleItemsCount: Int = 0
)
