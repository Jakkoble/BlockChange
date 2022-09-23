package de.jakkoble.modules.blocks.ressources

import org.bukkit.Material

enum class Ore(val material: Material) {
   COPPER(Material.COPPER_ORE),
   EMERALD(Material.EMERALD_BLOCK),
   GOLD(Material.GOLD_BLOCK),
   LAPIS(Material.LAPIS_ORE),
   QUARTZ(Material.NETHER_QUARTZ_ORE),
   REDSTONE(Material.REDSTONE_ORE),
   ANCIENT_DEBRIS(Material.ANCIENT_DEBRIS)
}
fun Ore.getMaterials(): List<Material> = Material.values().filter {
   it.isSolid && it.name.contains(name)
}
