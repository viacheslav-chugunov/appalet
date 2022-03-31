package viacheslav.chugunov.core.util

import viacheslav.chugunov.core.model.domain.ColorSet

interface ColorDescriptionFactory {
   val materialColors : List<ColorSet>
}