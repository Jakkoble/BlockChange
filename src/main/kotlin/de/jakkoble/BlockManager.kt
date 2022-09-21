package de.jakkoble

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.bukkit.Material
import org.bukkit.Registry.MATERIAL
import org.bukkit.entity.Player
import java.io.File

val userData = mutableListOf<UserData>()

class BlockManager {
   init {
      val configFile = File("plugins/DuelUltimate/userData.json")
      if (!configFile.exists()) {
         configFile.createNewFile()
         configFile.writeText(GsonBuilder().setPrettyPrinting().create().toJson(userData))
      }
   }
   fun load() {
      val configFile = File("plugins/DuelUltimate/userData.json")
      val data = Gson().fromJson<Collection<UserData>?>(configFile.readText(), object: TypeToken<MutableList<UserData>>() {}.type)
      if (data != null) userData.addAll(data)
   }
   fun generateRandomBlocks(player: Player) {
      val bannedMaterial = listOf(Material.AIR, Material.COMMAND_BLOCK, Material.COMMAND_BLOCK_MINECART, Material.REPEATING_COMMAND_BLOCK, Material.CHAIN_COMMAND_BLOCK)
      val materials = Material.values().all { it.isBlock && !it.isLegacy && !bannedMaterial.contains(it)}
      for (i in 1..randomBlocks)
         println("Run")
   }
}