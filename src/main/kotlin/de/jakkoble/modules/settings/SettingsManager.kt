package de.jakkoble.modules.settings

import de.jakkoble.utils.*
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.Inventory

class SettingsManager {
   fun getInventory(): Inventory {
      val inventory = Bukkit.createInventory(null, 5*9, Component.text("Einstellungen"))
      inventory.fillBorder()
      return inventory
   }
   fun setRolePeriod(intervall: Intervall) {
      blockIntervall = intervall
      Config().set(ConfigPath.BLOCK_INTERVALL, intervall.name)
   }
}