package de.jakkoble.modules.settings

import de.jakkoble.utils.Item
import de.jakkoble.utils.createItem
import de.jakkoble.utils.fillBorder
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
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
      inventory.setItem(20, createItem(
         material = Material.REPEATING_COMMAND_BLOCK,
         item = Item.NEW_BLOCKS,
         name = "Neue Blöcke für alle",
         color = NamedTextColor.GOLD,
         lore = listOf("Es bekommen alle Spieler neue zufällige Blöcke")
      ))
      return inventory
   }
   fun getConfimInventory(): Inventory {
      val inventory = Bukkit.createInventory(null, 5*9, Component.text("Einstellungen"))
      inventory.fillBorder()
      inventory.setItem(20, createItem(
         material = Material.GREEN_WOOL,
         item = Item.CONFIRM_RESET,
         name = "Bestätigen",
         color = NamedTextColor.GREEN,
         lore = listOf("Jeder Spieler erhält neue zufällig Blöcke")
      ))
      inventory.setItem(24, createItem(
         material = Material.RED_WOOL,
         item = Item.CANCEL_RESET,
         name = "Abbrechen",
         color = NamedTextColor.RED,
         lore = listOf("Aktion wird abgebrochen")
      ))
      inventory.setItem(36, createItem(
         material = Material.DARK_OAK_DOOR,
         item = Item.CANCEL_RESET,
         name = "Zurück",
         lore = listOf("Kehre zum Hauptmenü zurück")
      ))
      return inventory
   }
}