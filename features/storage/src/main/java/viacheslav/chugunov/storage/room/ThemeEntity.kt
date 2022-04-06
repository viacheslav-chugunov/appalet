package viacheslav.chugunov.storage.room

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.storage.room.model.RoomColorDescription
import viacheslav.chugunov.storage.room.model.RoomColorSet

@Entity(tableName = "THEMES")
class ThemeEntity(
    @PrimaryKey
    @ColumnInfo(name = "ID")
    var id: Long,
    @ColumnInfo(name = "TIME_ADDED")
    var timeAdded: Long,
    @Embedded(prefix = "COLORS_PRIMARY")
    override var colorsPrimary: RoomColorSet,
    @Embedded(prefix = "COLORS_SECONDARY")
    override var colorsSecondary: RoomColorSet,
    @Embedded(prefix = "COLOR_BACKGROUND")
    override var colorBackground: RoomColorDescription,
    @Embedded(prefix = "COLOR_ON_BACKGROUND")
    override var colorOnBackground: RoomColorDescription
) : Theme {

    constructor(theme: Theme) : this(
        id = 0,
        timeAdded = System.currentTimeMillis(),
        colorsPrimary = RoomColorSet(theme.colorsPrimary),
        colorsSecondary = RoomColorSet(theme.colorsSecondary),
        colorBackground = RoomColorDescription(theme.colorBackground),
        colorOnBackground = RoomColorDescription(theme.colorOnBackground)
    )

}