package de.jakkoble.modules.settings

import de.jakkoble.utils.Item
import de.jakkoble.utils.createItem
import de.jakkoble.utils.fillBorder
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.Inventory

class SettingsManager {
   fun getInventory(): Inventory {
      val inventory = Bukkit.createInventory(null, 5*9, Component.text("Einstellungen"))
      inventory.fillBorder()
      inventory.setItem(36, createItem(
         material = Material.DARK_OAK_DOOR,
         item = Item.BLOCK_RETURN,
         name = "Zurück",
         lore = listOf("Kehre zum Hauptmenü zurück")
      ))
      return inventory
   }
}