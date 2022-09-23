package de.jakkoble.modules.blocks.resources

import org.bukkit.Material

enum class ColoredBlocks(val material: Material) {
   WHITE(Material.WHITE_DYE),
   ORANGE(Material.ORANGE_DYE),
   MAGENTA(Material.MAGENTA_DYE),
   LIGHT_BLUE(Material.LIGHT_BLUE_DYE),
   YELLOW(Material.YELLOW_DYE),
   LIME(Material.LIME_DYE),
   PINK(Material.PINK_DYE),
   GRAY(Material.GRAY_DYE),
   LIGHT_GRAY(Material.LIGHT_GRAY_DYE),
   CYAN(Material.CYAN_DYE),
   PURPLE(Material.PURPLE_DYE),
   BLUE(Material.BLUE_DYE),
   BROWN(Material.BROWN_DYE),
   GREEN(Material.GREEN_DYE),
   RED(Material.RED_DYE),
   BLACK(Material.BLACK_DYE)
}
fun ColoredBlocks.getMaterials(): List<Material?> {
   val materials = mutableListOf<Material?>()
   materials.add(Material.getMaterial("${name}_WOOL"))
   materials.add(Material.getMaterial("${name}_TERRACOTTA"))
   materials.add(Material.getMaterial("${name}_CARPET"))
   materials.add(Material.getMaterial("${name}_STAINED_GLASS"))
   materials.add(Material.getMaterial("${name}_STAINED_GLASS_PANE"))
   materials.add(Material.getMaterial("${name}_SHULKER_BOX"))
   materials.add(Material.getMaterial("${name}_GLAZED_TERRACOTTA"))
   materials.add(Material.getMaterial("${name}_CONCRETE"))
   materials.add(Material.getMaterial("${name}_CONCRETE_POWDER"))
   materials.add(Material.getMaterial("${name}_DYE"))
   materials.add(Material.getMaterial("${name}_BED"))
   materials.add(Material.getMaterial("${name}_BANNER"))
   materials.add(Material.getMaterial("${name}_CANDLE"))
   return materials
}
