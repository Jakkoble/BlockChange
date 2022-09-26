package de.jakkoble.modules.blocks.resources

import org.bukkit.Material

enum class DefaultBlocks(val material: Material) {
   STONE(Material.STONE),
   COAL(Material.COAL_ORE),
   IRON(Material.IRON_ORE),
   DIAMOND(Material.DIAMOND_ORE),
   DIRT(Material.DIRT),
   CHEST(Material.CHEST),
   CRAFTING_TABLE(Material.CRAFTING_TABLE),
   SHULKER_BOX(Material.SHULKER_BOX),
   FURNACE(Material.FURNACE),
   TORCH(Material.TORCH),
   CLAY(Material.CLAY)
}
fun DefaultBlocks.getMaterials(): List<Material> {
   val materials = Material.values().filter {
      it.isSolid && !it.isLegacy && it.name.contains(name)
   }.toMutableList()
   materials.removeIf { it.name.contains("STONE") && (it.name != "STONE" && it.name != "COBBLESTONE")}
   return materials
}