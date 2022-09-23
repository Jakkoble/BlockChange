package de.jakkoble.modules.general

import de.jakkoble.Main
import de.jakkoble.utils.Item
import de.jakkoble.utils.itemType
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ItemSpawnEvent
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryMoveItemEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

class ItemLocker : Listener {
   @EventHandler
   fun onItemDrop(event: PlayerDropItemEvent) {
      if (event.itemDrop.itemStack.isLocked()) event.isCancelled = true
   }
   @EventHandler
   fun onItemSpawn(event: ItemSpawnEvent) {
      if (event.entity.itemStack.isLocked()) event.isCancelled = true
   }
   @EventHandler
   fun onItemMove(event: InventoryMoveItemEvent) {
      if (event.destination.holder is Player && (event.destination.holder as Player).gameMode == GameMode.CREATIVE) return
      if (event.item.isLocked()) event.isCancelled = true
   }
   @EventHandler
   fun onInventoryClick(event: InventoryClickEvent) {
      if (event.whoClicked.gameMode == GameMode.CREATIVE) return
      if (event.click == ClickType.NUMBER_KEY || event.currentItem?.isLocked() == true) event.isCancelled = true
   }
   private fun ItemStack.isLocked(): Boolean {
      if (type == Material.AIR || itemType() == Item.NONE) return false
      return itemMeta?.persistentDataContainer?.get(NamespacedKey(Main.INSTANCE, "itemType"), PersistentDataType.STRING) != null
   }
}