package de.jakkoble.modules.general

import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent
import de.jakkoble.Main
import de.jakkoble.commands.getSpawnLocation
import de.jakkoble.modules.blocks.BlockManager
import de.jakkoble.modules.blocks.getBlocks
import de.jakkoble.modules.blocks.sendNewBlockInfo
import de.jakkoble.modules.data.getPlayerData
import de.jakkoble.utils.allowedPlayers
import de.jakkoble.utils.savePrefix
import de.jakkoble.utils.serverOpen
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockDropItemEvent
import org.bukkit.event.inventory.CraftItemEvent
import org.bukkit.event.player.AsyncPlayerPreLoginEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerKickEvent
import org.bukkit.event.player.PlayerQuitEvent
import java.time.DayOfWeek
import java.time.LocalDateTime

class PlayerListener : Listener {
   @EventHandler
   fun onBreakBlock(event: BlockDropItemEvent) {
      if (event.items.isEmpty()) return
      if (!event.items.first().itemStack.type.isSolid) return
      if (!event.player.getBlocks().contains(event.blockState.type)) event.isCancelled = true
   }
   @EventHandler
   fun onPlayerJoin(event: PlayerJoinEvent) {
      val player = event.player
      event.joinMessage(savePrefix.append(Component.text(" ${event.player.name} ist dem Server beigetreten.").color(NamedTextColor.GRAY)))
      if (Main.INSTANCE.hasStarted() && getPlayerData(player.uniqueId.toString()) == null) {
         BlockManager().generateBlocks(player.name, player.uniqueId.toString())
         player.sendNewBlockInfo()
      }
      if (!Main.INSTANCE.hasStarted()) player.teleport(getSpawnLocation() ?: return)
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
   @EventHandler
   fun onPlayerPreLoginEvent(event: AsyncPlayerPreLoginEvent) {
      if (serverOpen || allowedPlayers.contains(event.uniqueId.toString())) return
      val openTime = when(LocalDateTime.now().dayOfWeek) {
         DayOfWeek.FRIDAY -> "ab 14:00 Uhr"
         else -> "ab 14:00 Uhr"
      }
      event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_FULL,
         Component.text("Der Server ist gerade geschlossen. Er hat heute ").color(NamedTextColor.RED)
            .append(Component.text(openTime).color(NamedTextColor.GOLD)
               .append(Component.text(" wieder geöffnet.").color(NamedTextColor.RED))))
   }
   @EventHandler
   fun onPlayerCraft(event: CraftItemEvent) {
      val item = event.currentItem ?: return
      if (!item.type.isBlock || item.type.isEmpty) return
      if (!(event.whoClicked as Player).getBlocks().contains(item.type)) event.isCancelled = true
   }
   @EventHandler
   fun onPlayerDeath(event: PlayerPostRespawnEvent) {
      val player = event.player
      if (player.bedSpawnLocation == null) player.teleport(getSpawnLocation() ?: return)
   }
}