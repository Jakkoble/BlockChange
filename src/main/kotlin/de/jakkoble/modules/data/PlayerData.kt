package de.jakkoble.modules.data

import de.jakkoble.modules.blocks.BlockManager
import de.jakkoble.modules.blocks.resources.*

data class PlayerData(val name: String, val uuid: String, val color: ColoredBlocks, val wood: Wood, val stone: Stone, val ore: Ore, val otherBlocks: MutableList<OtherBlocks>) {
   fun build() = BlockManager().addPlayer(this)
}
