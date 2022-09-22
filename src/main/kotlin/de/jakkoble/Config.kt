package de.jakkoble

import java.io.File
import java.util.*

enum class ConfigPath(val path: String) {
   RANDOM_BLOCKS("randomBlocks"),

}
class Config {
   fun load() {
      if (!File("plugins/FaisterSMP/config.yml").exists()) {
         val config = Main.INSTANCE.config
         config.set(ConfigPath.RANDOM_BLOCKS.path, 10)
         Main.INSTANCE.saveConfig()
      }
      randomBlocks = Main.INSTANCE.config.getLong(ConfigPath.RANDOM_BLOCKS.path)
   }
}