package de.jakkoble.modules.blocks

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import de.jakkoble.Main
import de.jakkoble.modules.blocks.resources.OtherBlocks
import de.jakkoble.modules.data.PlayerData
import de.jakkoble.modules.data.playerData
import de.jakkoble.utils.prefix
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.title.TitlePart
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.io.File

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
   fun generateBlocks(name: String, uuid: String) {
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
         uuid = uuid,
         otherBlocks = otherBlocks
      ).build()
   }
   fun regenerateAllBlocks() {
      playerData.forEach { BlockManager().generateBlocks(it.name, it.uuid) }
      Bukkit.getOnlinePlayers().forEach { it.sendNewBlockInfo() }
      println("$prefix Every Player got a new Block Pallete.")
   }
}
fun Player.sendNewBlockInfo() {
   sendMessage("")
   sendMessage("$prefix ${ChatColor.YELLOW}Du hast eine neue Block Pallete erhalten.")
   sendMessage("$prefix Tippe /b für eine Auflistung deiner verfügbaren Blöcke.")
   sendMessage("")
   sendTitlePart(TitlePart.TITLE, Component.text("Du hast neue Blöcke").color(NamedTextColor.GOLD))
}