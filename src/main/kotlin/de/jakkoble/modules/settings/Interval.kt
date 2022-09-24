package de.jakkoble.modules.settings

import de.jakkoble.utils.blockInterval
import de.jakkoble.utils.LoreElement
import net.kyori.adventure.text.format.NamedTextColor

enum class Interval(val displayName: String, val value: Long) {
   DEBUG("Debug", 60),
   ONE_DAY("1 Tag", 60*60*24),
   TWO_DAYS("2 Tage", 60*60*48),
   THREE_DAYS("3 Tage", 60*60*72),
   FOUR_DAYS("4 Tage", 60*60*96),
   FIVE_DAYS("5 Tage", 60*60*120),
   SIX_DAYS("6 Tage", 60*60*144),
   SEVEN_DAYS("7 Tage", 60*60*168)
}
fun Interval.format(): LoreElement {
   var color = NamedTextColor.DARK_GRAY
   var underlined = false
   if (this.name == blockInterval.name) {
      color = NamedTextColor.DARK_GRAY
      underlined = true
   }
   return LoreElement(
      content = displayName,
      color = color,
      underlined = underlined
   )
}
fun getBlockIntervall(name: String): Interval = Interval.values().first { it.name == name }