package de.jakkoble.modules.blocks.resources

import org.bukkit.Material

enum class Stone {
   ANDESITE,
   DRIPSTONE,
   BLACKSTONE,
   CALCITE,
   DEEPSLATE,
   SAND,
   TUFF,
   GRANITE,
   END_STONE,
   PRISMARINE,
   PURPUR,
   NETHERRACK
}
fun Stone.getMaterials(): List<Material> = Material.values().filter {
   it.isSolid && !it.isLegacy && it.name.contains(name) && !it.name.contains("ORE") && it.name != "SAND"
}