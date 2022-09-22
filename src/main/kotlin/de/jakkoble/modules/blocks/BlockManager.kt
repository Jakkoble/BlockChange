package de.jakkoble.modules.blocks

import de.jakkoble.Main
import org.bukkit.Material
import java.io.File

class BlockManager {
   init {
      Main.INSTANCE.saveConfig()
      val configFile = File("plugins/FaisterSMP/userData.json")
      if (!configFile.exists()) {
         configFile.createNewFile()
         //configFile.writeText(GsonBuilder().setPrettyPrinting().create().toJson(userData))
      }
   }
   fun load() {
      val configFile = File("plugins/FaisterSMP/userData.json")
      //val data = Gson().fromJson<Collection<UserData>?>(configFile.readText(), object: TypeToken<MutableList<UserData>>() {}.type)
      //if (data != null) userData.addAll(data)
   }
   fun generateRandomBlocks() {
      val materials = mutableListOf<Material>()
      materials.addAll(Wood.values()[(0 until Wood.values().size).random()].getMaterials())
      materials.addAll(Stone.values()[(0 until Stone.values().size).random()].getMaterials())
      materials.addAll(Ore.values()[(0 until Ore.values().size).random()].getMaterials())
      materials.addAll(DefaultBlocks.values())
   }
}