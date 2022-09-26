package de.jakkoble.modules.blocks.resources

import org.bukkit.Material

enum class OtherBlocks(val material: Material) {
   PODZOL(Material.PODZOL),
   GRASS_BLOCK(Material.GRASS_BLOCK),
   MUD(Material.MUD),
   AZALEA(Material.AZALEA_LEAVES),
   SPONGE(Material.SPONGE),
   TINTED_GLASS(Material.TINTED_GLASS),
   COBWEB(Material.COBWEB),
   WHITE_WOOL(Material.WHITE_WOOL),
   BRICK(Material.BRICKS),
   OBSIDIAN(Material.OBSIDIAN),
   END_ROD(Material.END_ROD),
   CHORUS(Material.CHORUS_FLOWER),
   PURPUR(Material.PURPUR_BLOCK),
   LADDER(Material.LADDER),
   SNOW(Material.SNOW),
   ICE(Material.ICE),
   CACTUS(Material.CACTUS),
   JUKEBOX(Material.JUKEBOX),
   PUMPKIN(Material.PUMPKIN),
   JACK_O_LANTERN(Material.JACK_O_LANTERN),
   SOUL(Material.SOUL_SAND),
   BASALT(Material.BASALT),
   GLOWSTONE(Material.GLOWSTONE),
   MUSHROOM(Material.RED_MUSHROOM_BLOCK),
   CHAIN(Material.CHAIN),
   MELON(Material.MELON),
   VINE(Material.VINE),
   GLOW_LICHEN(Material.GLOW_LICHEN),
   MYCELIUM(Material.MYCELIUM),
   LILY_PAD(Material.LILY_PAD),
   NETHER_BRICK(Material.NETHER_BRICKS),
   SCULK(Material.SCULK),
   ENCHANTING_TABLE(Material.ENCHANTING_TABLE),
   BEACON(Material.BEACON),
   ANVIL(Material.ANVIL),
   HAY_BLOCK(Material.HAY_BLOCK),
   SEA_LANTERN(Material.SEA_LANTERN),
   MAGMA_BLOCK(Material.MAGMA_BLOCK),
   WART_BLOCK(Material.NETHER_WART_BLOCK),
   BONE_BLOCK(Material.BONE_BLOCK),
   CORAL(Material.BUBBLE_CORAL_BLOCK),
   CONDUIT(Material.CONDUIT),
   SCAFFOLDING(Material.SCAFFOLDING),
   LECTERN(Material.LECTERN),
   TNT(Material.TNT),
   KELB(Material.DRIED_KELP_BLOCK),
   BEE(Material.BEE_NEST),
   CAMPFIRE(Material.CAMPFIRE),
   COMPOSTER(Material.COMPOSTER),
   BARREL(Material.BARREL),
   SMOKER(Material.SMOKER),
   CARTOGRAPHY(Material.CARTOGRAPHY_TABLE),
   FLETCHING(Material.FLETCHING_TABLE),
   GRINDSTONE(Material.GRINDSTONE),
   SMITHING_TABLE(Material.SMITHING_TABLE),
   STONECUTTER(Material.STONECUTTER),
   BELL(Material.BELL),
   LANTERN(Material.LANTERN),
   SHROOMLIGHT(Material.SHROOMLIGHT),
   HONEYCOMB(Material.HONEYCOMB),
   RESPAWN_ANCHOR(Material.RESPAWN_ANCHOR),
   FROGLIGHT(Material.OCHRE_FROGLIGHT),
   STONE_BRICKS(Material.STONE_BRICKS),
   SLIME(Material.SLIME_BLOCK),
   HONEY(Material.HONEY_BLOCK)
}
fun OtherBlocks.getMaterials(): List<Material> = Material.values().filter {
   it.isSolid && it.name.startsWith(name)
}