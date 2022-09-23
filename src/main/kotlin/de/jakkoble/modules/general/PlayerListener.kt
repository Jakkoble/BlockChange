package de.jakkoble.modules.general

import de.jakkoble.modules.blocks.resources.DefaultBlocks
import de.jakkoble.modules.blocks.resources.getMaterials
import de.jakkoble.modules.blocks.sendNewBlockInfo
import de.jakkoble.modules.data.getPlayerData
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockDropItemEvent
import org.bukkit.event.player.PlayerJoinEvent

class PlayerListener : Listener {
   @EventHandler
   fun onBreakBlock(event: BlockDropItemEvent) {
      val material = event.blockState.type
      val data = getPlayerData(event.player.uniqueId.toString()) ?: return
      if (data.color.getMaterials().contains(material)
         || data.wood.getMaterials().contains(material)
         || data.stone.getMaterials().contains(material)
         || data.ore.getMaterials().contains(material)
         || data.otherBlocks.flatMap { it.getMaterials() }.contains(material)
         || DefaultBlocks.values().flatMap { it.getMaterials() }.contains(material)) return
      event.isCancelled = true
   }
   @EventHandler
   fun onPlayerJoin(event: PlayerJoinEvent) {
      if (getPlayerData(event.player.uniqueId.toString())?.shouldNotify != true) return
      event.player.sendNewBlockInfo()
   }
}