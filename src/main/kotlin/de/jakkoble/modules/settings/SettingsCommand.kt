package de.jakkoble.modules.settings

import de.jakkoble.utils.prefix
import de.jakkoble.utils.transition
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SettingsCommand : CommandExecutor {
   override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
      if (sender !is Player) return true
      if (!sender.hasPermission("faister.settings")) {
         sender.sendMessage("$prefix Dafür hast du keine Rechte.")
         return true
      }
      val inventory = Bukkit.createInventory(null, 5*9, Component.text("Einstellungen"))
      sender.openInventory(inventory)
      inventory.transition(SettingsManager().getInventory())
      return true
   }
}