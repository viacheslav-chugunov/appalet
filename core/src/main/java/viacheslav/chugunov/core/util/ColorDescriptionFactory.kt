package viacheslav.chugunov.core.util

import viacheslav.chugunov.core.R
import viacheslav.chugunov.core.model.ColorDescription
import viacheslav.chugunov.core.model.ColorSet

interface ColorDescriptionFactory {
   val materialColors : List<ColorSet>
}