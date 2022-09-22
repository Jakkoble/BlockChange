package de.jakkoble.modules.blocks.ressources

import org.bukkit.Material

enum class Stone(val icon: Material) {
   ANDESITE(Material.ANDESITE),
   BLACKSTONE(Material.BLACKSTONE),
   CALCITE(Material.CALCITE),
   DEEPSLATE(Material.DEEPSLATE),
   SAND(Material.SAND),
   TUFF(Material.TUFF),
   GRANITE(Material.GRANITE),
   END_STONE(Material.END_STONE),
   PRISMARINE(Material.PRISMARINE),
   PURPUR(Material.PURPUR_BLOCK),
   NETHERRACK(Material.NETHERRACK)
}
fun Stone.getMaterials(): List<Material> = Material.values().filter {
   it.isSolid && !it.isLegacy && it.name.contains(name) && !it.name.contains("ORE")
}