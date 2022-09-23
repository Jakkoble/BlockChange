package de.jakkoble.modules.blocks

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import de.jakkoble.Main
import de.jakkoble.modules.blocks.resources.*
import de.jakkoble.modules.data.PlayerData
import org.bukkit.Material
import org.bukkit.entity.Player
import java.io.File

val playerData = mutableListOf<PlayerData>()
class BlockManager {
   init {
      Main.INSTANCE.saveConfig()
      val configFile = File("plugins/FaisterSMP/userData.json")
      if (!configFile.exists()) {
         configFile.createNewFile()
         configFile.writeText(GsonBuilder().setPrettyPrinting().create().toJson(playerData))
      }
   }
   fun load() {
      val configFile = File("plugins/FaisterSMP/userData.json")
      val data = Gson().fromJson<Collection<PlayerData>?>(configFile.readText(), object : TypeToken<MutableList<PlayerData>>() {}.type)
      if (data != null) playerData.addAll(data)
   }
   fun addPlayer(data: PlayerData) {
      if (playerData.map { it.uuid }.contains(data.uuid)) playerData.removeIf { it.uuid == data.uuid }
      playerData.add(data)
      val configFile = File("plugins/FaisterSMP/userData.json")
      configFile.writeText(GsonBuilder().setPrettyPrinting().create().toJson(playerData))
   }
   fun getPlayerData(uuid: String) = playerData.firstOrNull { it.uuid == uuid }
}
fun Player.generateBlocks() {
   val otherBlocks = mutableListOf<OtherBlocks>()
   for (i in 1..2) {
      lateinit var block: OtherBlocks
      do {
         block = OtherBlocks.values().random()
      } while (otherBlocks.contains(block))
      otherBlocks.add(block)
   }
   PlayerData(
      name = name,
      uuid = uniqueId.toString(),
      color = ColoredBlocks.values().random(),
      wood = Wood.values().random(),
      stone = Stone.values().random(),
      ore = Ore.values().random(),
      otherBlocks = otherBlocks
   ).build()
}
fun Player.isAllowedToGetBlock(material: Material): Boolean {
   val data = BlockManager().getPlayerData(uniqueId.toString()) ?: return false
   val otherMaterials = mutableListOf<Material>()
   data.otherBlocks.forEach { otherMaterials.addAll(it.getMaterials()) }
   val defaultBlocks = mutableListOf<Material>()
   DefaultBlocks.values().forEach { defaultBlocks.addAll(it.getMaterials()) }
   if (data.color.getMaterials().contains(material)
      || data.wood.getMaterials().contains(material)
      || data.stone.getMaterials().contains(material)
      || data.ore.getMaterials().contains(material)
      || otherMaterials.contains(material)
      || defaultBlocks.contains(material)) return true
   return false
}