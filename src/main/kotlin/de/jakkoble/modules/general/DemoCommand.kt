package de.jakkoble.modules.general

import de.jakkoble.utils.prefix
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class DemoCommand : CommandExecutor {
   override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
      if (args?.size != 1) {
         sender.sendMessage("$prefix Use /demo playerName")
         return true
      }
      val target = Bukkit.getPlayer(args[0])
      if (target == null) {
         sender.sendMessage("$prefix There is no Player online called ${args[0]}.")
         return true
      }
      target.showDemoScreen()
      sender.sendMessage("$prefix Sent Demo Screen to Player ${args[0]}.")
      return true
   }
}