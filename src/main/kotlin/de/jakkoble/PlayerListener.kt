package de.jakkoble

import de.jakkoble.modules.blocks.isAllowedToGetBlock
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockDropItemEvent
import org.bukkit.event.entity.ItemSpawnEvent

class PlayerListener : Listener {
   @EventHandler
   fun onBreakBlock(event: BlockDropItemEvent) {
      println(event.blockState.type)
      if (!event.player.isAllowedToGetBlock(event.blockState.type)) event.isCancelled = true
   }
}