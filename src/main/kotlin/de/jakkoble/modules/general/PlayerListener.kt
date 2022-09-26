package de.jakkoble.modules.general

import de.jakkoble.Main
import de.jakkoble.modules.blocks.BlockManager
import de.jakkoble.modules.blocks.resources.DefaultBlocks
import de.jakkoble.modules.blocks.resources.getMaterials
import de.jakkoble.modules.blocks.sendNewBlockInfo
import de.jakkoble.modules.data.getPlayerData
import de.jakkoble.utils.allowedPlayers
import de.jakkoble.utils.savePrefix
import de.jakkoble.utils.serverOpen
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockDropItemEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.player.AsyncPlayerPreLoginEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerKickEvent
import org.bukkit.event.player.PlayerQuitEvent
import java.time.DayOfWeek
import java.time.LocalDateTime

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
      if (Main.INSTANCE.hasStarted() && getPlayerData(player.uniqueId.toString()) == null) {
         BlockManager().generateBlocks(player.name, player.uniqueId.toString())
         player.sendNewBlockInfo()
      }
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
   fun onPlayerDamage(event: EntityDamageEvent) {
      if (!Main.INSTANCE.hasStarted()) event.isCancelled = true
   }
   @EventHandler
   fun onFoodLevelChange(event: FoodLevelChangeEvent) {
      if (!Main.INSTANCE.hasStarted()) event.isCancelled = true
   }
   @EventHandler
   fun onBlockBreak(event: BlockBreakEvent) {
      if (!Main.INSTANCE.hasStarted()) event.isCancelled = true
   }
}