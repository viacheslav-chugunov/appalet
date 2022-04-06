package viacheslav.chugunov.storage.room.model

import viacheslav.chugunov.core.model.ColorDescription
import viacheslav.chugunov.core.model.ColorSet

class RoomColorSet(
    regular: RoomColorDescription,
    onRegular: RoomColorDescription,
    light: RoomColorDescription,
    onLight: RoomColorDescription,
    dark: RoomColorDescription,
    onDark: RoomColorDescription
) : ColorSet.Default(regular, onRegular, light, onLight, dark, onDark) {

    constructor(colorSet: ColorSet) : this(
        regular = RoomColorDescription(colorSet.regular),
        onRegular = RoomColorDescription(colorSet.onRegular),
        light = RoomColorDescription(colorSet.light),
        onLight = RoomColorDescription(colorSet.onLight),
        dark = RoomColorDescription(colorSet.dark),
        onDark = RoomColorDescription(colorSet.onDark)
    )

}