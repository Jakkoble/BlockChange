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
   CLAY
}
fun DefaultBlocks.getMaterials(): List<Material> {
   val materials = Material.values().filter {
      it.isSolid && !it.isLegacy && it.name.contains(name)
   }.toMutableList()
   materials.removeIf { it.name.contains("STONE") && (it.name != "STONE" && !it.name.contains("COBBLESTONE"))}
   return materials
}