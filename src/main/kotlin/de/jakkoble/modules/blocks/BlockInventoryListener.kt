package de.jakkoble.modules.blocks

import de.jakkoble.utils.*
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
      when (item.itemType()) {
         Item.BLOCKS_COLOR -> {
            player.openInventory.topInventory.transition(BlockInventory(
               player = player,
               materialCategory = MaterialCategory.COLORED_BLOCKS
            ).create())
         }
         Item.PERSONAL_BLOCKS -> {
            player.openInventory.topInventory.transition(BlockInventory(
               player = player,
               materialCategory = MaterialCategory.PERSONAL_BLOCKS
            ).create())
         }
         Item.DEFAULT_BLOCKS -> {
            player.openInventory.topInventory.transition(BlockInventory(
               player = player,
               materialCategory = MaterialCategory.DEFAULT_BLOCKS
            ).create())
         }
         Item.BLOCK_RETURN -> {
            val oldInventory = player.openInventory.topInventory
            player.openInventory(oldInventory)
            oldInventory.transition(BlockManager().getInventory(event.whoClicked.uniqueId.toString()))
         }
         Item.BLOCK_DOWN -> {
            player.openInventory(BlockInventory(
               player = player,
               materialCategory = item.getPDC("materialCategory")?.getMaterialCategoryByName() ?: return,
               scrollIndex = item.getPDC("scrollIndex")?.toInt()?.plus(1) ?: return
            ).create())
         }
         Item.BLOCK_UP -> {
            player.openInventory(BlockInventory(
               player = player,
               materialCategory = item.getPDC("materialCategory")?.getMaterialCategoryByName() ?: return,
               scrollIndex = item.getPDC("scrollIndex")?.toInt()?.minus(1) ?: return
            ).create())
         }
         else -> return
      }
   }
}