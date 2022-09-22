package de.jakkoble

import de.jakkoble.enums.Wood
import org.bukkit.Material

data class UserData(val name: String, val uuid: String, val newBlocks: Boolean, val wood: Wood, val blocks: MutableList<Material>)