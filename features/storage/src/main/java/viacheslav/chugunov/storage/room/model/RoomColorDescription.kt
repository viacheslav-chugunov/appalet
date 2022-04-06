package viacheslav.chugunov.storage.room.model

import viacheslav.chugunov.core.model.ColorDescription

class RoomColorDescription(
    nameRes: Int,
    alpha: String,
    value: Long,
) : ColorDescription.Default(nameRes, alpha, value) {

    constructor(colorDescription: ColorDescription) : this(
        nameRes = colorDescription.nameRes,
        alpha = colorDescription.alpha,
        value = colorDescription.value
    )

}