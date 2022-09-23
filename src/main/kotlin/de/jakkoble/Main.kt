package de.jakkoble

import de.jakkoble.commands.StartCommand
import de.jakkoble.modules.blocks.BlockCommand
import de.jakkoble.modules.blocks.BlockManager
import de.jakkoble.modules.general.PlayerListener
import de.jakkoble.utils.*
import net.axay.kspigot.main.KSpigot
import kotlin.concurrent.thread
var running = true
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
      else println("$prefix Automatic Scheduler has not started because the Event has not yet begun.")
   }

   override fun shutdown() {
      running = false
   }
   fun hasStarted(): Boolean = Config().getLong(ConfigPath.LATEST_ROLL) > 0
}
fun startScheduler() {
   thread {
      println("Start Scheduler in ${Thread.currentThread().name} with id ${Thread.currentThread().id}")
      while(running) {
         Thread.sleep(1000)
         if (latestRole + rolePeriod >= System.currentTimeMillis() / 1000) continue
         BlockManager().regenerateAllBlocks()
      }
   }
}