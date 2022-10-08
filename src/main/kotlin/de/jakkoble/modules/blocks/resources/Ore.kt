package de.jakkoble.modules.blocks.resources

import org.bukkit.Material

enum class Ore {
   COPPER,
   REDSTONE,
   GOLD,
   LAPIS,
   QUARTZ,
   AMETHYST,
   ANCIENT_DEBRIS
}
fun Ore.getMaterials(): List<Material> {
   val materials = Material.values().filter {
      it.name.contains(name)
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
   materials.remove(Material.REDSTONE_WIRE)
   materials.remove(Material.REDSTONE_WALL_TORCH)
   return materials
}
