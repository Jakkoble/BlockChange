package de.jakkoble.modules.data

import de.jakkoble.modules.blocks.BlockManager
import de.jakkoble.modules.blocks.resources.*
val playerData = mutableListOf<PlayerData>()
data class PlayerData(
   val name: String,
   val uuid: String,
   var shouldNotify: Boolean = true,
   val color: ColoredBlocks = ColoredBlocks.values().random(),
   val wood: Wood = Wood.values().random(),
   val stone: Stone = Stone.values().random(),
   val ore: Ore = Ore.values().random(),
   val otherBlocks: MutableList<OtherBlocks>) {
   fun build() = BlockManager().addPlayer(this)
}
fun getPlayerData(uuid: String) = playerData.firstOrNull { it.uuid == uuid }
