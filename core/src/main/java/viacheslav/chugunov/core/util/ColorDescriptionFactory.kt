package viacheslav.chugunov.core.util

import viacheslav.chugunov.core.model.ColorSet
import viacheslav.chugunov.core.model.Coloring

interface ColorDescriptionFactory {
   val materialColorSets: List<ColorSet>
   val materialColors: List<Coloring>
}