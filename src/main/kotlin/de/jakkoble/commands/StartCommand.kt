package de.jakkoble.commands

import de.jakkoble.Main
import de.jakkoble.modules.blocks.BlockManager
import de.jakkoble.startScheduler
import de.jakkoble.utils.Config
import de.jakkoble.utils.ConfigPath
import de.jakkoble.utils.prefix
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class StartCommand : CommandExecutor {
   override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
      if (Main.INSTANCE.hasStarted()) {
         sender.sendMessage("$prefix Du hast das Event bereits gestartet.")
         return true
      }
      Config().set(ConfigPath.LATEST_ROLL, System.currentTimeMillis() / 1000)
      Bukkit.getOnlinePlayers().forEach { BlockManager().generateBlocks(it.name, it.uniqueId.toString()) }
      startScheduler()
      return true
   }
}