package de.jakkoble.utils

import de.jakkoble.Main
import java.io.File

enum class ConfigPath(val path: String) {
   RANDOM_BLOCKS("randomBlocks"),

}
class Config {
   fun load() {
      if (!File("plugins/FaisterSMP/config.yml").exists()) {
         val config = Main.INSTANCE.config
         config.set(ConfigPath.RANDOM_BLOCKS.path, 2)
         Main.INSTANCE.saveConfig()
      }
      randomBlocks = Main.INSTANCE.config.getLong(ConfigPath.RANDOM_BLOCKS.path)
   }
}