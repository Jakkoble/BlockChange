package de.jakkoble

import net.axay.kspigot.main.KSpigot
import org.bukkit.ChatColor

class Main : KSpigot() {
   companion object {
      lateinit var INSTANCE: Main
   }
   override fun load() {
      INSTANCE = this
   }
   override fun startup() {
      Config()
   }
}