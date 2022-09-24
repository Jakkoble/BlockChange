package de.jakkoble.modules.blocks

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import de.jakkoble.modules.blocks.resources.OtherBlocks
import de.jakkoble.modules.data.PlayerData
import de.jakkoble.modules.data.getPlayerData
import de.jakkoble.modules.data.playerData
import de.jakkoble.utils.*
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.title.TitlePart
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import java.io.File
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class BlockManager {
   init {
      val configFile = File("plugins/FaisterSMP/playerData.json")
      if (!configFile.exists()) {
         configFile.createNewFile()
         configFile.writeText(GsonBuilder().setPrettyPrinting().create().toJson(playerData))
      }
   }
   fun load() {
      val configFile = File("plugins/FaisterSMP/playerData.json")
      val data = Gson().fromJson<Collection<PlayerData>?>(configFile.readText(), object : TypeToken<MutableList<PlayerData>>() {}.type)
      if (data != null) playerData.addAll(data)
   }
   fun addPlayer(data: PlayerData) {
      if (playerData.map { it.uuid }.contains(data.uuid)) playerData.removeIf { it.uuid == data.uuid }
      playerData.add(data)
      val configFile = File("plugins/FaisterSMP/playerData.json")
      configFile.writeText(GsonBuilder().setPrettyPrinting().create().toJson(playerData))
   }
   fun getInventory(uuid: String): Inventory {
      val inventory = Bukkit.createInventory(null, 5*9, Component.text("Blöcke"))
      inventory.fillBorder()
      val data = getPlayerData(uuid) ?: return inventory
      inventory.setItem(4, createItem(
         material = Material.KNOWLEDGE_BOOK,
         item = Item.TIME_INFO,
         name = "Neue Blöcke in",
         lore = listOf((latestRole + blockIntervall.value).format())
      ))
      inventory.setItem(20, createItem(
         material = data.color.material,
         item = Item.BLOCKS_COLOR,
         name = "Deine Farbe ist ${data.color.displayName}",
         lore = listOf("Blöcke in dieser Farbe kannst du verwenden", "(Klick für Block Übersicht)")
      ))
      inventory.setItem(22, createItem(
         material = Material.PLAYER_HEAD,
         item = Item.PERSONAL_BLOCKS,
         name = "Persönliche Blöcke",
         lore = listOf("Zufällig ausgewählte Blöcke", "(Klick für Block Übersicht)")
      ))
      inventory.setItem(24, createItem(
         material = Material.GRASS_BLOCK,
         item = Item.DEFAULT_BLOCKS,
         name = "Standard Blöcke",
         lore = listOf("Blöcke die jeder abbauen/craften kann", "(Klick für Block Übersicht)")
      ))
      return inventory
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
      val time = System.currentTimeMillis() / 1000
      Config().set(ConfigPath.LATEST_ROLL, time)
      latestRole = time
      println("$prefix Every Player got a new Block Pallete.")
   }
}
fun Player.sendNewBlockInfo() {
   sendMessage("")
   sendMessage("$prefix ${ChatColor.YELLOW}Du hast eine neue Block Pallete erhalten.")
   sendMessage("$prefix Tippe /b für eine Auflistung deiner verfügbaren Blöcke.")
   sendMessage("")
   sendTitlePart(TitlePart.TITLE, Component.text("Neue Block Pallete").color(NamedTextColor.GOLD))
   playSound(location, Sound.ITEM_GOAT_HORN_SOUND_1, 0.5f, 1f)
}
private fun Long.format(): String = LocalDateTime.ofEpochSecond(this, 0, ZoneOffset.ofHours(2)).format(DateTimeFormatter.ofPattern("d. MMMM yyyy H:mm"))