package de.jakkoble

import de.jakkoble.commands.StartCommand
import de.jakkoble.modules.blocks.BlockCommand
import de.jakkoble.modules.blocks.BlockManager
import de.jakkoble.modules.general.PlayerListener
import de.jakkoble.utils.Config
import de.jakkoble.utils.ConfigPath
import de.jakkoble.utils.latestRole
import de.jakkoble.utils.rolePeriod
import net.axay.kspigot.main.KSpigot
import kotlin.concurrent.thread

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
      if (hasStarted()) startScheduler()
   }
   fun hasStarted(): Boolean = config.get(ConfigPath.LATEST_ROLL.path) != null
}
fun startScheduler() {
   thread {
      println("Start Scheduler in ${Thread.currentThread().name} with id ${Thread.currentThread().id}")
      while(true) {
         Thread.sleep(1000)
         if (latestRole + rolePeriod >= System.currentTimeMillis() / 1000) continue
         Config().set(ConfigPath.LATEST_ROLL, System.currentTimeMillis() / 1000)
         BlockManager().regenerateAllBlocks()
      }
   }
}