package de.jakkoble.modules.blocks.resources

import org.bukkit.Material

enum class ColoredBlocks(val material: Material, val displayName: String) {
   WHITE(Material.WHITE_DYE, "Weiß"),
   ORANGE(Material.ORANGE_DYE, "Orange"),
   MAGENTA(Material.MAGENTA_DYE, "Magenta"),
   LIGHT_BLUE(Material.LIGHT_BLUE_DYE, "Hellblau"),
   YELLOW(Material.YELLOW_DYE, "Gelb"),
   LIME(Material.LIME_DYE, "Hellgrün"),
   PINK(Material.PINK_DYE, "Pink"),
   GRAY(Material.GRAY_DYE, "Grau"),
   LIGHT_GRAY(Material.LIGHT_GRAY_DYE, "Hellgrau"),
   CYAN(Material.CYAN_DYE, "Türkies"),
   PURPLE(Material.PURPLE_DYE, "Violett"),
   BLUE(Material.BLUE_DYE, "Blau"),
   BROWN(Material.BROWN_DYE, "Braun"),
   GREEN(Material.GREEN_DYE, "Grün"),
   RED(Material.RED_DYE, "Rot"),
   BLACK(Material.BLACK_DYE, "Schwarz")
}
fun ColoredBlocks.getMaterials(): List<Material?> {
   val materials = mutableListOf<Material?>()
   materials.add(Material.getMaterial("${name}_WOOL"))
   materials.add(Material.getMaterial("${name}_TERRACOTTA"))
   materials.add(Material.getMaterial("${name}_CARPET"))
   materials.add(Material.getMaterial("${name}_STAINED_GLASS"))
   materials.add(Material.getMaterial("${name}_STAINED_GLASS_PANE"))
   materials.add(Material.getMaterial("${name}_GLAZED_TERRACOTTA"))
   materials.add(Material.getMaterial("${name}_CONCRETE"))
   materials.add(Material.getMaterial("${name}_CONCRETE_POWDER"))
   materials.add(Material.getMaterial("${name}_DYE"))
   materials.add(Material.getMaterial("${name}_BED"))
   materials.add(Material.getMaterial("${name}_BANNER"))
   materials.add(Material.getMaterial("${name}_CANDLE"))
   return materials
}
