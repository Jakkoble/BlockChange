package de.jakkoble.modules.blocks.resources

import org.bukkit.Material

enum class Wood {
   ACACIA,
   BIRCH,
   DARK_OAK,
   JUNGLE,
   OAK,
   SPRUCE,
   MANGROVE,
   CRIMSON,
   WARPED
}
fun Wood.getMaterials(): List<Material> = Material.values().filter {
   it.isBlock && it.name.contains(name)
      && !it.name.startsWith("PETRIFIED_${name}")
      && !it.name.startsWith("POTTED_${name}") }