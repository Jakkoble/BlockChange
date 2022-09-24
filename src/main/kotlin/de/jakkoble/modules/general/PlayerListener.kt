package de.jakkoble.modules.general

import de.jakkoble.modules.blocks.BlockManager
import de.jakkoble.modules.blocks.resources.DefaultBlocks
import de.jakkoble.modules.blocks.resources.getMaterials
import de.jakkoble.modules.blocks.sendNewBlockInfo
import de.jakkoble.modules.data.getPlayerData
import de.jakkoble.utils.savePrefix
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockDropItemEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerKickEvent
import org.bukkit.event.player.PlayerQuitEvent

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
      val player = event.player
      event.joinMessage(savePrefix.append(Component.text(" ${event.player.name} ist dem Server beigetreten.").color(NamedTextColor.GRAY)))
      if (getPlayerData(player.uniqueId.toString())?.shouldNotify != true) return
      player.sendNewBlockInfo()
      val data = getPlayerData(player.uniqueId.toString()) ?: return
      data.shouldNotify = false
      BlockManager().addPlayer(data)
   }
   @EventHandler
   fun onPlayerQuit(event: PlayerQuitEvent) {
      event.quitMessage(savePrefix.append(Component.text(" ${event.player.name} hat den Server verlassen.").color(NamedTextColor.GRAY)))
   }
   @EventHandler
   fun onPlayerKick(event: PlayerKickEvent) {
      event.leaveMessage(savePrefix.append(Component.text(" ${event.player.name} hat den Server verlassen.").color(NamedTextColor.GRAY)))
   }
}