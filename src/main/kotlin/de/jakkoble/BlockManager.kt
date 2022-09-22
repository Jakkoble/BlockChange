package de.jakkoble

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import de.jakkoble.enums.Wood
import org.bukkit.Material
import org.bukkit.Registry.MATERIAL
import org.bukkit.entity.Player
import java.io.File
import kotlin.random.Random

val userData = mutableListOf<UserData>()

class BlockManager {
   init {
      /*Main.INSTANCE.saveConfig()
      val configFile = File("plugins/DuelUltimate/userData.json")
      if (!configFile.exists()) {
         configFile.createNewFile()
         configFile.writeText(GsonBuilder().setPrettyPrinting().create().toJson(userData))
      }*/
   }
   fun load() {
      val configFile = File("plugins/FaisterSMP/userData.json")
      val data = Gson().fromJson<Collection<UserData>?>(configFile.readText(), object: TypeToken<MutableList<UserData>>() {}.type)
      if (data != null) userData.addAll(data)
   }
   fun generateRandomBlocks() {
      val bannedMaterial = listOf(Material.AIR, Material.COMMAND_BLOCK, Material.COMMAND_BLOCK_MINECART, Material.REPEATING_COMMAND_BLOCK, Material.CHAIN_COMMAND_BLOCK)
      val materials = Material.values().filter { it.isBlock && !it.isLegacy && !bannedMaterial.contains(it)}
      println("Materials Size: ${materials}")
      val wood = Wood.values()[Random.nextInt(0, Wood.values().size - 1)]
      println("Wood: $wood")
      for (i in 1..randomBlocks)
         println("Run $i")
   }
}
fun Player.newBlocks(): Boolean = Main.INSTANCE.config.getBoolean(ConfigPath.RANDOM_BLOCKS.path)