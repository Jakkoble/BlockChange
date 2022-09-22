package de.jakkoble

import de.jakkoble.commands.TestCommand
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
      Config().load()
      getCommand("test")?.setExecutor(TestCommand())
   }
}