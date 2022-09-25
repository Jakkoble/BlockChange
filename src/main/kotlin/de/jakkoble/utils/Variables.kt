package de.jakkoble.utils

import de.jakkoble.modules.settings.Interval
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.ChatColor

val prefix = "${ChatColor.GOLD}FaisterSMP ${ChatColor.WHITE}•${ChatColor.GRAY}"
val savePrefix = Component.text()
   .append(Component.text("FaisterSMP ").color(NamedTextColor.GOLD))
   .append(Component.text("•").color(NamedTextColor.WHITE))
   .build()
var randomBlocks: Long = 0
var latestRole: Long = 0
var blockInterval: Interval = Interval.THREE_DAYS
var serverOpen: Boolean = true
var allowedPlayers: List<String> = listOf()
fun openServer(open: Boolean) {
   if (serverOpen == open) return
   serverOpen = open
   if (open) {
      println("$prefix Server is now opened.")
      Bukkit.getOnlinePlayers().forEach { it.sendMessage("$prefix Der Server ist nun für alle Spieler geöffnet.") }
   }
   else {
      val players = Bukkit.getOnlinePlayers()
      println("$prefix Server is now closed. (Online Players: ${players.size}")
      players.forEach {
         if (!allowedPlayers.contains(it.uniqueId.toString())) it.kick(Component.text("Der Server ist nun bis morgen 14:00 Uhr geschlossen.").color(NamedTextColor.RED))
         it.sendMessage("$prefix Der Server ist nun für alle Spieler geschlossen")
      }
   }
}