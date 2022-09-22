package de.jakkoble

import de.jakkoble.commands.TestCommand
import de.jakkoble.utils.Config
import net.axay.kspigot.main.KSpigot

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