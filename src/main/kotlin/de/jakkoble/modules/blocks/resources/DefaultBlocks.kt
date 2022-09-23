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
   FURNACE(Material.FURNACE)
}
fun DefaultBlocks.getMaterials(): List<Material> = Material.values().filter {
   it.isSolid && !it.isLegacy && it.name.contains(name)
}