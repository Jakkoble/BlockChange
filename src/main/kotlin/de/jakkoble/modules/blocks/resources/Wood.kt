package de.jakkoble.modules.blocks.resources

import org.bukkit.Material

enum class Wood(val material: Material) {
   ACACIA(Material.ACACIA_WOOD),
   BIRCH(Material.BIRCH_WOOD),
   DARK_OAK(Material.DARK_OAK_WOOD),
   JUNGLE(Material.JUNGLE_WOOD),
   OAK(Material.OAK_WOOD),
   SPRUCE(Material.SPRUCE_WOOD),
   MANGROVE(Material.MANGROVE_WOOD),
   CRIMSON(Material.CRIMSON_STEM),
   WARPED(Material.WARPED_STEM)
}
fun Wood.getMaterials(): List<Material> = Material.values().filter {
   it.isBlock && it.name.contains(name)
      && !it.name.startsWith("PETRIFIED_${name}")
      && !it.name.startsWith("POTTED_${name}") }