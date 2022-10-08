package de.jakkoble.modules.blocks

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import de.jakkoble.Main
import de.jakkoble.modules.blocks.resources.*
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
      val configFile = File("plugins/${Main.INSTANCE.description.name}/playerData.json")
      if (!configFile.exists()) {
         configFile.createNewFile()
         configFile.writeText(GsonBuilder().setPrettyPrinting().create().toJson(playerData))
      }
   }
   fun load() {
      Main.INSTANCE.saveConfig()
      println("plugins/${Main.INSTANCE.description.fullName}/playerData.json")
      val configFile = File("plugins/${Main.INSTANCE.description.name}/playerData.json")
      val data = Gson().fromJson<Collection<PlayerData>?>(configFile.readText(), object : TypeToken<MutableList<PlayerData>>() {}.type)
      if (data != null) playerData.addAll(data)
   }
   fun addPlayer(data: PlayerData) {
      if (playerData.map { it.uuid }.contains(data.uuid)) playerData.removeIf { it.uuid == data.uuid }
      playerData.add(data)
      val configFile = File("plugins/${Main.INSTANCE.description.name}/playerData.json")
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
      val data = getPlayerData(uuid)
      val shouldNotify = !(Bukkit.getPlayer(UUID.fromString(uuid))?.isOnline ?: false)
      lateinit var playerData: PlayerData
      if (data != null) {
         // Generate new Color
         lateinit var color: ColoredBlocks
         do {
            color = ColoredBlocks.values().random()
         } while (data.color == color)

         // Generate new Wood
         lateinit var wood: Wood
         do {
            wood = Wood.values().random()
         } while (data.wood == wood)

         // Generate new Stone
         lateinit var stone: Stone
         do {
            stone = Stone.values().random()
         } while (data.stone == stone)

         // Generate new Ore
         lateinit var ore: Ore
         do {
            ore = Ore.values().random()
         } while (data.ore == ore)

         // Generate new other Blocks
         val otherBlocks: MutableList<OtherBlocks> = mutableListOf()
         for (i in 1..randomBlocks) {
            lateinit var block: OtherBlocks
            do {
               block = OtherBlocks.values().random()
            } while (data.otherBlocks.contains(block) || otherBlocks.contains(block))
            otherBlocks.add(block)
         }

         playerData = PlayerData(
            name = name,
            uuid = uuid,
            shouldNotify = shouldNotify,
            color = color,
            wood = wood,
            stone = stone,
            ore = ore,
            otherBlocks = otherBlocks
         )
      } else {
         val otherBlocks = mutableListOf<OtherBlocks>()
         for (i in 1..2) {
            lateinit var block: OtherBlocks
            do {
               block = OtherBlocks.values().random()
            } while (otherBlocks.contains(block))
            otherBlocks.add(block)
         }
         playerData = PlayerData(
            name = name,
            uuid = uuid,
            shouldNotify = shouldNotify,
            otherBlocks = otherBlocks,
         )
      }
      playerData.build()
   }
   fun regenerateAllBlocks() {
      val data = mutableListOf<PlayerData>()
      playerData.forEach { data.add(it) }
      data.forEach { BlockManager().generateBlocks(it.name, it.uuid) }
      Bukkit.getOnlinePlayers().forEach { it.sendNewBlockInfo() }
      val time = System.currentTimeMillis() / 1000
      Config().set(ConfigPath.LATEST_ROLL, time)
      latestRoll = time
      server.consoleSender.sendMessage("$prefix Every Player got a new Block Pallet.")
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
fun Player.getBlocks(): List<Material> {
   val blocks = mutableListOf<Material>()
   val data = getPlayerData(uniqueId.toString()) ?: return blocks
   data.color.getMaterials().forEach {
      if (it != null) blocks.add(it)
   }
   blocks.addAll(data.wood.getMaterials())
   blocks.addAll(data.stone.getMaterials())
   blocks.addAll(data.ore.getMaterials())
   data.otherBlocks.flatMap { it.getMaterials() }.forEach { material ->
      if (material != null) blocks.add(material)
   }
   blocks.addAll(DefaultBlocks.values().flatMap { it.getMaterials() })
   return blocks
}
private fun Long.format(): String = LocalDateTime.ofEpochSecond(this, 0, ZoneOffset.ofHours(2)).format(DateTimeFormatter.ofPattern("d. MMMM, H:mm").withLocale(Locale.GERMAN)) + " Uhr"
