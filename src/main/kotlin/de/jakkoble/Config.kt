package de.jakkoble

import java.io.File
import java.util.*

class Config {
   fun load() {
      if (!File("plugins/FaisterSMP/config.yml").exists()) {
         val config = Main.INSTANCE.config
         config.set("randomBlocks", 10)
         config.set("newRollInSeconds", 3*60)
         config.set("startDate", System.currentTimeMillis() / 1000)
         Main.INSTANCE.saveConfig()
      }
      randomBlocks = getLong("randomBlocks")
   }
   fun getLong(path: String): Long = Main.INSTANCE.config.getLong(path)
   fun get(path: String) = Main.INSTANCE.config.get(path)
}