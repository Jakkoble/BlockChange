package de.jakkoble.utils

import de.jakkoble.modules.settings.Interval
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.ChatColor

val prefix = "${ChatColor.GOLD}FaisterSMP ${ChatColor.WHITE}•${ChatColor.GRAY}"
val savePrefix = Component.text()
   .append(Component.text("FaisterSMP ").color(NamedTextColor.GOLD))
   .append(Component.text("•").color(NamedTextColor.WHITE))
   .build()
var randomBlocks: Long = 0
var latestRole: Long = 0
var blockInterval: Interval = Interval.THREE_DAYS