package de.jakkoble.commands

import de.jakkoble.utils.prefix
import de.jakkoble.utils.transition
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class InvseeCommand : CommandExecutor {
   override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
      if (sender !is Player) return true
      if (args?.size != 1) {
         sender.sendMessage("$prefix Bitte verwende /invsee spielerName.")
         return true
      }
      val target = Bukkit.getPlayer(args[0])
      if (target == null) {
         sender.sendMessage("$prefix Der angegebene Spieler ist nicht online.")
         return true
      }
      if (target == sender) {
         sender.sendMessage("$prefix Nice try.")
         return true
      }
      val targetInventory = target.inventory
      val inventory = Bukkit.createInventory(null, targetInventory.size, Component.text("${target.name}s Inventar"))
      sender.openInventory(inventory)
      inventory.transition(targetInventory)
      return true
   }
}