package viacheslav.chugunov.storage.room

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.storage.room.model.RoomColorDescription
import viacheslav.chugunov.storage.room.model.RoomColorSet

@Entity(
    tableName = "THEMES",
    primaryKeys = [
        "COLORS_PRIMARY_REGULAR_NAME_ORDINAL",
        "COLORS_PRIMARY_REGULAR_ALPHA",
        "COLORS_PRIMARY_REGULAR_VALUE",
        "COLORS_PRIMARY_ON_REGULAR_NAME_ORDINAL",
        "COLORS_PRIMARY_ON_REGULAR_ALPHA",
        "COLORS_PRIMARY_ON_REGULAR_VALUE",
        "COLORS_PRIMARY_LIGHT_NAME_ORDINAL",
        "COLORS_PRIMARY_LIGHT_ALPHA",
        "COLORS_PRIMARY_LIGHT_VALUE",
        "COLORS_PRIMARY_ON_LIGHT_NAME_ORDINAL",
        "COLORS_PRIMARY_ON_LIGHT_ALPHA",
        "COLORS_PRIMARY_ON_LIGHT_VALUE",
        "COLORS_PRIMARY_DARK_NAME_ORDINAL",
        "COLORS_PRIMARY_DARK_ALPHA",
        "COLORS_PRIMARY_DARK_VALUE",
        "COLORS_PRIMARY_ON_DARK_NAME_ORDINAL",
        "COLORS_PRIMARY_ON_DARK_ALPHA",
        "COLORS_PRIMARY_ON_DARK_VALUE",
        "COLOR_BACKGROUND_NAME_ORDINAL",
        "COLOR_BACKGROUND_COLOR_ALPHA",
        "COLOR_BACKGROUND_COLOR_VALUE",
        "COLOR_ON_BACKGROUND_NAME_ORDINAL",
        "COLOR_ON_BACKGROUND_COLOR_ALPHA",
        "COLOR_ON_BACKGROUND_COLOR_VALUE"
    ]
)
class ThemeEntity(
    @ColumnInfo(name = "TIME_ADDED")
    var timeAdded: Long,
    @Embedded(prefix = "COLORS_PRIMARY_")
    override var colorsPrimary: RoomColorSet,
    @Embedded(prefix = "COLORS_SECONDARY_")
    override var colorsSecondary: RoomColorSet,
    @Embedded(prefix = "COLOR_BACKGROUND_")
    override var colorBackground: RoomColorDescription,
    @Embedded(prefix = "COLOR_ON_BACKGROUND_")
    override var colorOnBackground: RoomColorDescription
) : Theme.Default() {

    constructor(theme: Theme) : this(
        timeAdded = System.currentTimeMillis(),
        colorsPrimary = RoomColorSet(theme.colorsPrimary),
        colorsSecondary = RoomColorSet(theme.colorsSecondary),
        colorBackground = RoomColorDescription(theme.colorBackground),
        colorOnBackground = RoomColorDescription(theme.colorOnBackground)
    )

}