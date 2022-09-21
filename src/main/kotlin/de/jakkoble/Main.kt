package de.jakkoble

import net.axay.kspigot.main.KSpigot

class Main : KSpigot() {
   companion object {
      lateinit var INSTANCE: Main
   }
   override fun load() {
      INSTANCE = this
   }
   override fun startup() {
      println("Hello World!")
      saveConfig()
   }
}