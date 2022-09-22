package de.jakkoble

import de.jakkoble.commands.TestCommand
import de.jakkoble.modules.blocks.BlockManager
import de.jakkoble.modules.blocks.generateBlocks
import de.jakkoble.utils.Config
import net.axay.kspigot.event.listen
import net.axay.kspigot.main.KSpigot
import org.bukkit.event.player.PlayerJoinEvent

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
      getCommand("test")?.setExecutor(TestCommand())
      server.pluginManager.registerEvents(PlayerListener(), this)
      listen<PlayerJoinEvent> { event ->
         event.player.generateBlocks()
      }
   }
}