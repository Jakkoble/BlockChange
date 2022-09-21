package de.jakkoble

import org.bukkit.Material

data class UserData(val name: String, val uuid: String, val blocks: MutableList<Material>)