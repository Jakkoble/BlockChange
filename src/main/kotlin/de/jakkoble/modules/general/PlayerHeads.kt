package de.jakkoble.modules.general

import com.destroystokyo.paper.profile.ProfileProperty
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.util.*

lateinit var pageUP: ItemStack
lateinit var pageDown: ItemStack

class PlayerHeads {
   fun load() {
      pageUP = createCustomSkull(
         name = "Vorherige Blöcke",
         value = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzA0MGZlODM2YTZjMmZiZDJjN2E5YzhlYzZiZTUxNzRmZGRmMWFjMjBmNTVlMzY2MTU2ZmE1ZjcxMmUxMCJ9fX0="
      )
      pageDown = createCustomSkull(
         name = "Weitere Blöcke",
         value = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzQzNzM0NmQ4YmRhNzhkNTI1ZDE5ZjU0MGE5NWU0ZTc5ZGFlZGE3OTVjYmM1YTEzMjU2MjM2MzEyY2YifX19"
      )
   }
}
private fun createCustomSkull(name: String, value: String): ItemStack {
   val item = ItemStack(Material.PLAYER_HEAD)
   val skullMeta = item.itemMeta as SkullMeta
   val profile = Bukkit.createProfileExact(UUID.randomUUID(), "Skull")
   profile.setProperty(ProfileProperty("textures", value, value))
   skullMeta.playerProfile = profile
   skullMeta.displayName(Component.text(name).color(NamedTextColor.WHITE))
   return item
}