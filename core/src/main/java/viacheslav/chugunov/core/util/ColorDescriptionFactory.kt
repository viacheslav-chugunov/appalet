package viacheslav.chugunov.core.util

import viacheslav.chugunov.core.model.ColorSet

interface ColorDescriptionFactory {
   val materialColors : List<ColorSet>
}