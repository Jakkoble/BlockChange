package de.jakkoble.utils

import de.jakkoble.Main
import de.jakkoble.modules.settings.Interval
import net.axay.kspigot.extensions.server
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.scheduler.BukkitRunnable

val prefix = "${ChatColor.GOLD}BlockChange ${ChatColor.WHITE}•${ChatColor.GRAY}"
val savePrefix = Component.text()
   .append(Component.text("BlockChange ").color(NamedTextColor.GOLD))
   .append(Component.text("•").color(NamedTextColor.WHITE))
   .build()
var randomBlocks: Long = 0
var latestRoll: Long = 0
var blockInterval: Interval = Interval.THREE_DAYS
var serverOpen: Boolean = true
var allowedPlayers: List<String> = listOf()
fun openServer(open: Boolean) {
   if (serverOpen == open) return
   serverOpen = open
   object: BukkitRunnable() {
      override fun run() {
         if (open) {
            server.consoleSender.sendMessage("$prefix Server is now opened.")
            Bukkit.getOnlinePlayers().forEach { it.sendMessage("$prefix Der Server ist nun für alle Spieler geöffnet.") }
         } else {
            val players = Main.INSTANCE.server.onlinePlayers
            server.consoleSender.sendMessage("$prefix Server is now closed - kicked ${players.filter { !allowedPlayers.contains(it.uniqueId.toString()) }.size} players.")
            players.forEach {
               if (!allowedPlayers.contains(it.uniqueId.toString())) it.kick(Component.text("Der Server ist nun bis morgen 14:00 Uhr geschlossen.").color(NamedTextColor.RED))
               it.sendMessage("$prefix Der Server ist nun für alle Spieler geschlossen")
            }
         }
      }
   }.runTask(Main.INSTANCE)
}