package de.jakkoble.commands

import de.jakkoble.Main
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

class SetSpawnCommand : CommandExecutor, TabCompleter {
   override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
      if (sender !is Player) return true
      val config = Main.INSTANCE.config
      config.set("Spawn.World", sender.location.world)
      config.set("Spawn.X", sender.location.x)
      config.set("Spawn.Y", sender.location.y)
      config.set("Spawn.Z", sender.location.z)
      config.set("Spawn.Yaw", sender.location.z)
      config.set("Spawn.Pitch", sender.location.pitch)
      Main.INSTANCE.saveConfig()
      return true
   }
   override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>?): MutableList<String> = mutableListOf("")
}
fun getSpawnLocation(): Location? {
   val config = Main.INSTANCE.config
   val defaultSpawn = Bukkit.getWorld("world")?.spawnLocation
   if (config.getString("Spawn.World") == null) return defaultSpawn
   return Location(
      Bukkit.getWorld(config.getString("Spawn.World") ?: return defaultSpawn),
      config.getDouble("Spawn.X"),
      config.getDouble("Spawn.Y"),
      config.getDouble("Spawn.Z"),
      config.getDouble("Spawn.Yaw").toFloat(),
      config.getDouble("Spawn.Pitch").toFloat())
}