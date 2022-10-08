package de.jakkoble.modules.blocks.resources

import org.bukkit.Material

enum class OtherBlocks {
   PODZOL,
   GRASS_BLOCK,
   MUD,
   AZALEA,
   SPONGE,
   TINTED_GLASS,
   COBWEB,
   WHITE_WOOL,
   BRICK,
   OBSIDIAN,
   END_ROD,
   CHORUS,
   PURPUR,
   LADDER,
   SNOW,
   ICE,
   CACTUS,
   JUKEBOX,
   PUMPKIN,
   JACK_O_LANTERN,
   SOUL,
   BASALT,
   GLOWSTONE,
   MUSHROOM,
   CHAIN,
   MELON,
   VINE,
   GLOW_LICHEN,
   MYCELIUM,
   LILY_PAD,
   NETHER_BRICK,
   SCULK,
   ENCHANTING_TABLE,
   BEACON,
   ANVIL,
   HAY_BLOCK,
   SEA_LANTERN,
   MAGMA_BLOCK,
   WART_BLOCK,
   BONE_BLOCK,
   CORAL,
   CONDUIT,
   SCAFFOLDING,
   LECTERN,
   TNT,
   KELB,
   BEE,
   COMPOSTER,
   BARREL,
   SMOKER,
   CARTOGRAPHY,
   FLETCHING,
   GRINDSTONE,
   SMITHING_TABLE,
   STONECUTTER,
   BELL,
   LANTERN,
   SHROOMLIGHT,
   HONEYCOMB,
   RESPAWN_ANCHOR,
   FROGLIGHT,
   STONE_BRICK,
   SLIME,
   HONEY,
   AMETHYST
}
fun OtherBlocks.getMaterials(): List<Material?> {
   val materials = Material.values().filter {
      it.isSolid && it.name.startsWith(name)
   }.toMutableList()
   if (this == OtherBlocks.SPONGE) materials.add(Material.WET_SPONGE)
   if (this == OtherBlocks.AMETHYST) materials.addAll(Material.values().filter { it.name.contains(name) && !it.isSolid})
   if (this == OtherBlocks.SOUL) materials.removeAll(listOf(Material.SOUL_CAMPFIRE, Material.SOUL_LANTERN))
   return materials
}