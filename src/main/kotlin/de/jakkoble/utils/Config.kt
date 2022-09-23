package de.jakkoble.utils

import de.jakkoble.Main
import de.jakkoble.modules.settings.Intervall
import de.jakkoble.modules.settings.getBlockIntervall
import java.io.File

enum class ConfigPath(val path: String) {
   RANDOM_BLOCKS("randomBlocks"),
   LATEST_ROLL("latestRoll"),
   BLOCK_INTERVALL("blockIntervall")
}
class Config {
   fun load() {
      if (!File("plugins/FaisterSMP/config.yml").exists()) {
         val config = Main.INSTANCE.config
         config.set(ConfigPath.RANDOM_BLOCKS.path, 2)
         config.set(ConfigPath.BLOCK_INTERVALL.path, Intervall.THREE_DAYS.name)
         config.set(ConfigPath.LATEST_ROLL.path, 0)
         Main.INSTANCE.saveConfig()
      }
      randomBlocks = getLong(ConfigPath.RANDOM_BLOCKS)
      latestRole = getLong(ConfigPath.LATEST_ROLL)
      blockIntervall = getBlockIntervall(Main.INSTANCE.config.getString(ConfigPath.BLOCK_INTERVALL.path) ?: Intervall.THREE_DAYS.name)
   }
   fun getLong(configPath: ConfigPath) = Main.INSTANCE.config.getLong(configPath.path)
   fun set(configPath: ConfigPath, content: Any?) {
      Main.INSTANCE.config.set(configPath.path, content)
      Main.INSTANCE.saveConfig()
   }
}