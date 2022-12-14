package de.jakkoble.modules.blocks.resources

import org.bukkit.Material

enum class DefaultBlocks {
   STONE,
   COAL,
   IRON,
   EMERALD,
   DIAMOND,
   DIRT,
   GRASS_BLOCK,
   CHEST,
   CRAFTING_TABLE,
   SHULKER_BOX,
   FURNACE,
   CLAY,
   SAND,
   GRAVEL,
   CAMPFIRE,
   LANTERN,
   FLOWER_POT
}
fun DefaultBlocks.getMaterials(): List<Material> {
   val materials = Material.values().filter {
      it.isSolid && !it.isLegacy && it.name.contains(name)
   }.toMutableList()
   materials.removeIf { it.name.contains("STONE_BRICK")
      || it.name.contains("REDSTONE")
      || it.name.contains("BLACKSTONE")
      || it.name.contains("END_STONE")
      || it.name.contains("DRIPSTONE")
      || it.name.contains("GLOWSTONE")}
   materials.removeIf {
      it.name.contains("SAND") && it.name != "SAND"
   }
   return materials
}