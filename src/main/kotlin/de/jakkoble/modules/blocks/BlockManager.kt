package de.jakkoble.modules.blocks

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import de.jakkoble.modules.blocks.resources.DefaultBlocks
import de.jakkoble.modules.blocks.resources.OtherBlocks
import de.jakkoble.modules.blocks.resources.getMaterials
import de.jakkoble.modules.data.PlayerData
import de.jakkoble.modules.data.getPlayerData
import de.jakkoble.modules.data.playerData
import de.jakkoble.modules.general.addLore
import de.jakkoble.modules.general.information
import de.jakkoble.modules.general.newBlocks
import de.jakkoble.utils.*
import net.axay.kspigot.extensions.server
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
import java.util.*

class BlockManager {
   init {
      val configFile = File("plugins/BlockChange/playerData.json")
      if (!configFile.exists()) {
         configFile.createNewFile()
         configFile.writeText(GsonBuilder().setPrettyPrinting().create().toJson(playerData))
      }
   }
   fun load() {
      val configFile = File("plugins/BlockChange/playerData.json")
      val data = Gson().fromJson<Collection<PlayerData>?>(configFile.readText(), object : TypeToken<MutableList<PlayerData>>() {}.type)
      if (data != null) playerData.addAll(data)
   }
   fun addPlayer(data: PlayerData) {
      if (playerData.map { it.uuid }.contains(data.uuid)) playerData.removeIf { it.uuid == data.uuid }
      playerData.add(data)
      val configFile = File("plugins/BlockChange/playerData.json")
      configFile.writeText(GsonBuilder().setPrettyPrinting().create().toJson(playerData))
   }
   fun getInventory(player: Player): Inventory {
      val inventory = Bukkit.createInventory(null, 5*9, Component.text("Blöcke"))
      inventory.fillBorder()
      val data = getPlayerData(player.uniqueId.toString()) ?: return inventory
      inventory.setItem(4, information.addLore((latestRoll + blockInterval.value).format()))
      if (player.hasPermission("faister.resetBlocks")) inventory.setItem(8, newBlocks.addLore("Es bekommen alle Spieler neue zufällig Blöcke"))
      inventory.setItem(20, createItem(
         material = data.color.material,
         item = Item.BLOCKS_COLOR,
         name = "Deine Farbe ist ${data.color.displayName}",
         lore = listOf("Alle Blöcke deiner Farbe")
      ))
      inventory.setItem(22, createPlayerHead(
         player = Bukkit.getOfflinePlayer(UUID.fromString(player.uniqueId.toString())),
         item = Item.PERSONAL_BLOCKS,
         name = "Persönliche Blöcke",
         color = NamedTextColor.LIGHT_PURPLE,
         lore = listOf("Deine zufällig ausgewählten Blöcke")
      ))
      inventory.setItem(24, createItem(
         material = Material.GRASS_BLOCK,
         item = Item.DEFAULT_BLOCKS,
         name = "Standard Blöcke",
         color = NamedTextColor.DARK_GREEN,
         lore = listOf("Blöcke die jeder abbauen/craften kann")
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
         otherBlocks = otherBlocks,
         shouldNotify = !(Bukkit.getPlayer(UUID.fromString(uuid))?.isOnline ?: false)
      ).build()

   }
   fun regenerateAllBlocks() {
      playerData.forEach { BlockManager().generateBlocks(it.name, it.uuid) }
      Bukkit.getOnlinePlayers().forEach { it.sendNewBlockInfo() }
      val time = System.currentTimeMillis() / 1000
      Config().set(ConfigPath.LATEST_ROLL, time)
      latestRoll = time
      server.consoleSender.sendMessage("$prefix Every Player got a new Block Pallete.")
   }
}
fun Player.sendNewBlockInfo() {
   sendMessage("")
   sendMessage("$prefix ${ChatColor.YELLOW}Du hast eine neue Block Palette erhalten.")
   sendMessage("$prefix Tippe /b für eine Auflistung deiner verfügbaren Blöcke.")
   sendMessage("")
   sendTitlePart(TitlePart.TITLE, Component.text("Neue Block Palette").color(NamedTextColor.GOLD))
   playSound(location, Sound.ITEM_GOAT_HORN_SOUND_1, 0.5f, 1f)
}
fun Player.getBlocks(): List<Material?> {
   val blocks = mutableListOf<Material?>()
   val data = getPlayerData(uniqueId.toString()) ?: return blocks
   blocks.addAll(data.color.getMaterials())
   blocks.addAll(data.wood.getMaterials())
   blocks.addAll(data.stone.getMaterials())
   blocks.addAll(data.ore.getMaterials())
   blocks.addAll(data.otherBlocks.flatMap { it.getMaterials() })
   blocks.addAll(DefaultBlocks.values().flatMap { it.getMaterials() })
   return blocks
}
private fun Long.format(): String = LocalDateTime.ofEpochSecond(this, 0, ZoneOffset.ofHours(2)).format(DateTimeFormatter.ofPattern("d. MMMM, H:mm").withLocale(Locale.GERMAN)) + " Uhr"
