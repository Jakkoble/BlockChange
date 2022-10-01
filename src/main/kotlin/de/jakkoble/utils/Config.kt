package de.jakkoble.utils

import de.jakkoble.Main
import de.jakkoble.modules.settings.Interval
import de.jakkoble.modules.settings.getBlockIntervall
import java.io.File

enum class ConfigPath(val path: String) {
   RANDOM_BLOCKS("randomBlocks"),
   LATEST_ROLL("latestRoll"),
   BLOCK_INTERVALL("blockIntervall"),
   ALLOWED_PLAYER("allowedPlayer")
}
class Config {
   fun load() {
      Main.INSTANCE.saveConfig()
      if (!File("plugins/BlockChange/config.yml").exists()) {
         val config = Main.INSTANCE.config
         config.set(ConfigPath.RANDOM_BLOCKS.path, 2)
         config.set(ConfigPath.BLOCK_INTERVALL.path, Interval.FIVE_DAYS.name)
         config.set(ConfigPath.LATEST_ROLL.path, 0)
         config.set(ConfigPath.ALLOWED_PLAYER.path, listOf("847ca4e9-18bc-49fd-bce5-2cfcdab388d1", "5718618e-e420-460a-8326-cf96f0b21770"))
         Main.INSTANCE.saveConfig()
      }
      randomBlocks = getLong(ConfigPath.RANDOM_BLOCKS)
      latestRoll = getLong(ConfigPath.LATEST_ROLL)
      blockInterval = getBlockIntervall(Main.INSTANCE.config.getString(ConfigPath.BLOCK_INTERVALL.path) ?: Interval.FIVE_DAYS.name)
      allowedPlayers = Main.INSTANCE.config.getStringList(ConfigPath.ALLOWED_PLAYER.path)
      println("$randomBlocks, $latestRoll, $blockInterval")
   }
   fun getLong(configPath: ConfigPath) = Main.INSTANCE.config.getLong(configPath.path)
   fun set(configPath: ConfigPath, content: Any?) {
      Main.INSTANCE.config.set(configPath.path, content)
      Main.INSTANCE.saveConfig()
   }
}