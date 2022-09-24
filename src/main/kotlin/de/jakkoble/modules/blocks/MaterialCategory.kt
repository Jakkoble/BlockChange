package de.jakkoble.modules.blocks

import de.jakkoble.modules.blocks.resources.DefaultBlocks
import de.jakkoble.modules.blocks.resources.getMaterials
import de.jakkoble.modules.data.PlayerData
import org.bukkit.Material

enum class MaterialCategory() {
   COLORED_BLOCKS,
   DEFAULT_BLOCKS,
   PERSONAL_BLOCKS
}
fun String.getMaterialCategoryByName(): MaterialCategory? = MaterialCategory.values().firstOrNull { it.name == this }
fun getMaterialListByCategory(materialCategory: MaterialCategory, data: PlayerData): List<Material?> = when (materialCategory) {
   MaterialCategory.COLORED_BLOCKS -> data.color.getMaterials()
   MaterialCategory.DEFAULT_BLOCKS -> DefaultBlocks.values().flatMap { it.getMaterials() }
   MaterialCategory.PERSONAL_BLOCKS -> getPersonalBlocks(data)
}
private fun getPersonalBlocks(data: PlayerData): List<Material> {
   val materials = mutableListOf<Material>()
   materials.addAll(data.ore.getMaterials())
   materials.addAll(data.otherBlocks.flatMap { it.getMaterials() })
   materials.addAll(data.stone.getMaterials())
   materials.addAll(data.wood.getMaterials())
   materials.removeIf { it.name.startsWith("POTTED") || it.name.contains("WALL_SIGN") }
   return materials
}