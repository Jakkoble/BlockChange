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
   CAMPFIRE,
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
   STONE_BRICKS,
   SLIME,
   HONEY
}
fun OtherBlocks.getMaterials(): List<Material> = Material.values().filter {
   it.isSolid && it.name.startsWith(name)
}