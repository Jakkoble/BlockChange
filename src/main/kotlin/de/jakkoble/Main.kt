package de.jakkoble

import de.jakkoble.commands.InvseeCommand
import de.jakkoble.commands.SetSpawnCommand
import de.jakkoble.commands.StartCommand
import de.jakkoble.modules.blocks.BlockCommand
import de.jakkoble.modules.blocks.BlockInventoryListener
import de.jakkoble.modules.blocks.BlockManager
import de.jakkoble.modules.general.DemoCommand
import de.jakkoble.modules.general.ItemLocker
import de.jakkoble.modules.general.PlayerHeads
import de.jakkoble.modules.general.PlayerListener
import de.jakkoble.utils.*
import net.axay.kspigot.main.KSpigot
import org.bukkit.scheduler.BukkitRunnable
import java.time.DayOfWeek
import java.time.LocalDateTime

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
      PlayerHeads().load()

      getCommand("block")?.setExecutor(BlockCommand())
      getCommand("block")?.tabCompleter = BlockCommand()

      getCommand("start")?.setExecutor(StartCommand())
      getCommand("start")?.tabCompleter = StartCommand()

      getCommand("invsee")?.setExecutor(InvseeCommand())

      getCommand("setspawn")?.setExecutor(SetSpawnCommand())
      getCommand("setspawn")?.tabCompleter = SetSpawnCommand()

      getCommand("demo")?.setExecutor(DemoCommand())

      server.pluginManager.registerEvents(PlayerListener(), this)
      server.pluginManager.registerEvents(ItemLocker(), this)
      server.pluginManager.registerEvents(BlockInventoryListener(), this)
      if (hasStarted()) startScheduler()
      else server.consoleSender.sendMessage("$prefix Automatic Scheduler has not started because the Event has not begun yet.")
   }
   override fun shutdown() {
      running = false
   }
   fun hasStarted(): Boolean = Config().getLong(ConfigPath.LATEST_ROLL) > 0
}
fun startScheduler() {
   object: BukkitRunnable() {
      override fun run() {
         val localTime = LocalDateTime.now()
         when (localTime.dayOfWeek) {
            DayOfWeek.FRIDAY -> openServer(localTime.hour >= 14)
            DayOfWeek.SATURDAY -> openServer(true)
            DayOfWeek.SUNDAY -> openServer(true)
            else -> openServer(localTime.hour !in 2..13)
         }
         //if (System.currentTimeMillis() / 1000 >= latestRoll + blockInterval.value) BlockManager().regenerateAllBlocks()
      }
   }.runTaskTimerAsynchronously(Main.INSTANCE, 0, 20)
}