package de.jakkoble.commands

import de.jakkoble.BlockManager
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class TestCommand : CommandExecutor {
   override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
      BlockManager().generateRandomBlocks()
      return true
   }
}