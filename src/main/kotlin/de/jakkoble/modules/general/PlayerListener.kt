package de.jakkoble.modules.general

import de.jakkoble.modules.blocks.generateBlocks
import de.jakkoble.modules.blocks.isAllowedToGetBlock
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockDropItemEvent
import org.bukkit.event.player.PlayerJoinEvent

class PlayerListener : Listener {
   @EventHandler
   fun onBreakBlock(event: BlockDropItemEvent) {
      if (!event.player.isAllowedToGetBlock(event.blockState.type)) event.isCancelled = true
   }
   @EventHandler
   fun onPlayerJoin(event: PlayerJoinEvent) = event.player.generateBlocks()
}