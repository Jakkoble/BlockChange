package de.jakkoble.modules.blocks

import de.jakkoble.modules.data.getPlayerData
import de.jakkoble.utils.CustomPDC
import de.jakkoble.utils.Item
import de.jakkoble.utils.createItem
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class BlockInventory(val player: Player, val materialCategory: MaterialCategory, val scrollIndex: Int = 0) {
   fun create(): Inventory {
      val inventory = Bukkit.createInventory(null, 5*9, Component.text("Blöcke"))
      val placeholder = createItem(
         material = Material.GRAY_STAINED_GLASS_PANE,
         item = Item.PLACEHOLDER
      )
      for (i in 0..8) inventory.setItem(i, placeholder)
      for (i in 37..44) inventory.setItem(i, placeholder)
      inventory.setItem(36, createItem(
         material = Material.DARK_OAK_DOOR,
         item = Item.BLOCK_RETURN,
         name = "Zurück",
         lore = listOf("Kehre zu den Einstellungen zurück")
      ))
      val materials = getMaterialListByCategory(
         materialCategory = materialCategory,
         data = getPlayerData(player.uniqueId.toString()) ?: throw(IllegalStateException("Player has no PlayerData"))
      )
      val slots = inventory.size - 18
      if (materials.size - scrollIndex * 9 > slots) inventory.setItem(inventory.size - 1, createItem(
         material = Material.JACK_O_LANTERN,
         item = Item.BLOCK_DOWN,
         name = "Weitere Blöcke",
         customPDC = listOf(
            CustomPDC("materialCategory", materialCategory.name),
            CustomPDC("scrollIndex", scrollIndex)
         )
      ))
      if (scrollIndex > 0) inventory.setItem(8, createItem(
         material = Material.CARVED_PUMPKIN,
         item = Item.BLOCK_UP,
         name = "Vorherige Blöcke",
         customPDC = listOf(
            CustomPDC("materialCategory", materialCategory.name),
            CustomPDC("scrollIndex", scrollIndex)
         )
      ))
      for (i in 0..minOf(materials.size, slots - 1)) inventory.setItem(i + 9, createItem(
         material = materials.getOrNull(i + scrollIndex * 9) ?: return inventory,
         item = Item.BLOCK
      ))
      return inventory
   }
}