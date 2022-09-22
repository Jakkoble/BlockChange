package de.jakkoble

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import de.jakkoble.ressources.Ore
import de.jakkoble.ressources.Stone
import de.jakkoble.ressources.Wood
import de.jakkoble.ressources.getMaterials
import java.io.File

val userData = mutableListOf<UserData>()

class BlockManager {
   init {
      Main.INSTANCE.saveConfig()
      val configFile = File("plugins/FaisterSMP/userData.json")
      if (!configFile.exists()) {
         configFile.createNewFile()
         configFile.writeText(GsonBuilder().setPrettyPrinting().create().toJson(userData))
      }
   }
   fun load() {
      val configFile = File("plugins/FaisterSMP/userData.json")
      val data = Gson().fromJson<Collection<UserData>?>(configFile.readText(), object: TypeToken<MutableList<UserData>>() {}.type)
      if (data != null) userData.addAll(data)
   }
   fun generateRandomBlocks() {
      val wood = Wood.values()[(0 until Wood.values().size).random()]
      println("Wood: $wood")
      println(wood.getMaterials())
      val stone = Stone.values()[(0 until Stone.values().size).random()]
      println("Stone: $stone")
      println(stone.getMaterials())
      val ore = Ore.values()[(0 until Ore.values().size).random()]
      println("Ore: $ore")
      println(ore.getMaterials())
   }
}