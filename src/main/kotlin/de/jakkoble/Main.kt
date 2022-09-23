package de.jakkoble

import de.jakkoble.commands.StartCommand
import de.jakkoble.modules.blocks.BlockCommand
import de.jakkoble.modules.blocks.BlockManager
import de.jakkoble.modules.general.PlayerListener
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
      BlockManager().load()
      getCommand("block")?.setExecutor(BlockCommand())
      getCommand("start")?.setExecutor(StartCommand())
      server.pluginManager.registerEvents(PlayerListener(), this)
   }
}