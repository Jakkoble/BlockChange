package de.jakkoble.modules.blocks.ressources

import org.bukkit.Material

enum class OtherBlocks(val material: Material) {
   MUD(Material.MUD_BRICKS),
   OBSIDIAN(Material.OBSIDIAN),
   ICE(Material.ICE),
   SNOW(Material.SNOW_BLOCK),
   CLAY(Material.CLAY),
   PUMPKIN(Material.PUMPKIN),
   SOUL(Material.SOUL_SAND),
   BASALT(Material.BASALT),
   GLOWSTONE(Material.GLOWSTONE),
   MELON(Material.MELON),
   NETHER_BRICK(Material.NETHER_BRICKS),
   TERRACOTA(Material.TERRACOTTA),
   HAY(Material.HAY_BLOCK),
   SEA_LANTERN(Material.SEA_LANTERN),
   WART(Material.NETHER_WART),
   CONCRETE(Material.PURPLE_CONCRETE),
   CORAL(Material.BUBBLE_CORAL_BLOCK),
   KELB(Material.DRIED_KELP_BLOCK)
}
fun OtherBlocks.getMaterials(): List<Material> = Material.values().filter {
   it.isSolid && it.name.startsWith(name)
}