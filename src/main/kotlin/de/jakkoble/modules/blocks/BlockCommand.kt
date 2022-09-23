package de.jakkoble.modules.blocks

import de.jakkoble.utils.transition
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class BlockCommand : CommandExecutor {
   override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
      if (sender !is Player) return true
      val inventory = Bukkit.createInventory(null, 5*9, Component.text("Blöcke"))
      sender.openInventory(inventory)
      inventory.transition(BlockManager().getInventory(sender.uniqueId.toString()))
      return true
   }
}