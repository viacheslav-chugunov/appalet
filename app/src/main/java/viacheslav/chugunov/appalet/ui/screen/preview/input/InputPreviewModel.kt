package viacheslav.chugunov.appalet.ui.screen.preview.input

data class InputPreviewModel(
    val input1: String = "",
    val input2: String = "",
    val uiEnabled: Boolean = true
) {
    val buttonVisible: Boolean = uiEnabled && (input1.isNotBlank() || input2.isNotBlank())
}
