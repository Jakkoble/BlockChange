package de.jakkoble.utils

import de.jakkoble.Main
import java.io.File

enum class ConfigPath(val path: String) {
   RANDOM_BLOCKS("randomBlocks"),
   LATEST_ROLL("latestRoll"),
   ROLE_PERIOD("rolePeriod")
}
class Config {
   fun load() {
      if (!File("plugins/FaisterSMP/config.yml").exists()) {
         val config = Main.INSTANCE.config
         config.set(ConfigPath.RANDOM_BLOCKS.path, 2)
         config.set(ConfigPath.ROLE_PERIOD.path, 60)
         config.set(ConfigPath.LATEST_ROLL.path, 0)
         Main.INSTANCE.saveConfig()
      }
      randomBlocks = getLong(ConfigPath.RANDOM_BLOCKS)
      latestRole = getLong(ConfigPath.LATEST_ROLL)
      rolePeriod = getLong(ConfigPath.ROLE_PERIOD)
   }
   fun getLong(configPath: ConfigPath) = Main.INSTANCE.config.getLong(configPath.path)
   fun set(configPath: ConfigPath, content: Any?) {
      Main.INSTANCE.config.set(configPath.path, content)
      Main.INSTANCE.saveConfig()
   }
}