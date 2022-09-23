package de.jakkoble.modules.blocks

import de.jakkoble.modules.blocks.resources.getMaterials
import de.jakkoble.modules.data.getPlayerData
import de.jakkoble.utils.*
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class BlockInventoryListener : Listener {
   @EventHandler
   fun onInventoryClick(event: InventoryClickEvent) {
      val item = event.currentItem
      if (item == null || item.type == Material.AIR || event.whoClicked !is Player) return
      val player = event.whoClicked as Player
      val returnItem = createItem(
         material = Material.DARK_OAK_DOOR,
         item = Item.BLOCK_RETURN,
         name = "Zurück",
         lore = listOf("Kehre zur Übersicht zurück")
      )
      when (item.itemType()) {
         Item.BLOCKS_COLOR -> {
            val data = getPlayerData(event.whoClicked.uniqueId.toString()) ?: return
            val inventory = Bukkit.createInventory(null, 5*9, Component.text("Farbblöcke"))
            inventory.fillBorder()
            inventory.setItem(36, returnItem)
            val materials = data.color.getMaterials()
            var slot = 10
            for (i in materials.indices) {
               inventory.setItem(slot, createItem(
                  material = materials.getOrNull(i) ?: return,
                  item = Item.BLOCK
               ))
               slot++
            }
            val oldInventory = player.openInventory.topInventory
            player.openInventory(oldInventory)
            oldInventory.transition(inventory)
         }
         Item.PERSONAL_BLOCKS -> {
            val data = getPlayerData(event.whoClicked.uniqueId.toString()) ?: return
            val inventory = Bukkit.createInventory(null, 5*9, Component.text("Persönliche Blöcke"))
            inventory.fillBorder()
            inventory.setItem(39, returnItem)
            val materials = mutableListOf<Material>()
            materials.addAll(data.ore.getMaterials())
            materials.addAll(data.stone.getMaterials())
            materials.addAll(data.wood.getMaterials())
            materials.addAll(data.otherBlocks.flatMap { it.getMaterials() })
            var slot = 10
            for (i in materials.indices) {
               inventory.setItem(slot, createItem(
                  material = materials.getOrNull(i) ?: return,
                  item = Item.BLOCK
               ))
               slot++
            }
            val oldInventory = player.openInventory.topInventory
            player.openInventory(oldInventory)
            oldInventory.transition(inventory)
         }
         Item.BLOCK_RETURN -> {
            val oldInventory = player.openInventory.topInventory
            player.openInventory(oldInventory)
            oldInventory.transition(BlockManager().getInventory(event.whoClicked.uniqueId.toString()))
         }
         else -> return
      }
   }
}