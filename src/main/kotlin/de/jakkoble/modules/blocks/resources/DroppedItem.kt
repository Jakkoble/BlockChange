package de.jakkoble.modules.blocks.resources

import org.bukkit.Material

enum class DroppedItem {
   COBWEB,
   GRASS,
   FERN,
   AZALEA,
   DEAD_BUSH,
   SEAGRASS,
   SEA_PICKLE,
   DANDELION,
   POPPY,
   BLUE_ORCHID,
   ALLIUM,
   AZARE_BLUET,
   TULIP,
   DAISY,
   CORDFLOWER,
   VALLEY,
   WITHER_ROSE,
   BROWN_MUSHROOM,
   RED_MUSHROOM,
   FUNGUS,
   ROOTS,
   SUGAR_CANE,
   KELP,
   BAMBOO,
   TORCH,
   CHORUS,
   LADDER,
   SNOWBALL,
   VINES,
   LICHEN,
   MELON,
   SUNFLOWER,
   LILAC,
   ROSE_BUSH,
   PEONY,
   TURTLE_EGG,
   CORAL,
   STRING,
   ITEM_FRAME,
   BEETROOT,
   CARROT,
   BERRIES,
   HONEY,
   AZURE_BLUET,
   CORNFLOWER,
   AMETHYST,
   FLINT,
   FLOWER_POT
}
fun getDroppableItems(): List<Material> {
   val items = mutableListOf<Material>()
   DroppedItem.values().forEach { droppedItems ->
      items.addAll(Material.values().filter { it.name.contains(droppedItems.name) })
   }
   return items
}