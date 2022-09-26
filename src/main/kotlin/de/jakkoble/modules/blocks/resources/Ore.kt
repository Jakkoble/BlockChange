package de.jakkoble.modules.blocks.resources

import org.bukkit.Material

enum class Ore(val material: Material) {
   COPPER(Material.COPPER_ORE),
   EMERALD(Material.EMERALD_BLOCK),
   GOLD(Material.GOLD_BLOCK),
   LAPIS(Material.LAPIS_ORE),
   QUARTZ(Material.NETHER_QUARTZ_ORE),
   REDSTONE(Material.REDSTONE_ORE),
   AMETYST_BLOCK(Material.AMETHYST_BLOCK),
   ANCIENT_DEBRIS(Material.ANCIENT_DEBRIS)
}
fun Ore.getMaterials(): List<Material> {
   val materials = Material.values().filter {
      it.isSolid && it.name.contains(name)
   }.toMutableList()
   if (this != Ore.REDSTONE) return materials
   materials.add(Material.REPEATER)
   materials.add(Material.COMPARATOR)
   materials.add(Material.PISTON)
   materials.add(Material.STICKY_PISTON)
   materials.add(Material.OBSERVER)
   materials.add(Material.HOPPER)
   materials.add(Material.DISPENSER)
   materials.add(Material.DROPPER)
   materials.add(Material.LECTERN)
   materials.add(Material.TARGET)
   materials.add(Material.LEVER)
   materials.add(Material.LIGHTNING_ROD)
   materials.add(Material.DAYLIGHT_DETECTOR)
   materials.add(Material.TRIPWIRE_HOOK)
   materials.add(Material.REDSTONE_LAMP)
   return materials
}
