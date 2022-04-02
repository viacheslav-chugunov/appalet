package viacheslav.chugunov.appalet.ui.screen.preview.list

data class ListPreviewModel(
    val items: List<Int> = IntRange(0, ITEMS_LIMIT - 1).toList(),
    val uiEnabled: Boolean = true
) {
    val showRefresh: Boolean = items.size < ITEMS_LIMIT

    companion object {
        const val ITEMS_LIMIT = 35
    }
}
