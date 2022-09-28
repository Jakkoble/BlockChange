package de.jakkoble.modules.general

import com.destroystokyo.paper.profile.ProfileProperty
import de.jakkoble.Main
import de.jakkoble.utils.CustomPDC
import de.jakkoble.utils.Item
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.persistence.PersistentDataType
import java.util.*

lateinit var pageUP: ItemStack
lateinit var pageDown: ItemStack
lateinit var back: ItemStack
lateinit var information: ItemStack
lateinit var newBlocks: ItemStack
class PlayerHeads {
   fun load() {
      pageUP = createCustomSkull(
         name = "Vorherige Blöcke",
         value = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTk5YWFmMjQ1NmE2MTIyZGU4ZjZiNjI2ODNmMmJjMmVlZDlhYmI4MWZkNWJlYTFiNGMyM2E1ODE1NmI2NjkifX19",
         item = Item.BLOCK_UP,
      )
      pageDown = createCustomSkull(
         name = "Weitere Blöcke",
         value = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzkxMmQ0NWIxYzc4Y2MyMjQ1MjcyM2VlNjZiYTJkMTU3NzdjYzI4ODU2OGQ2YzFiNjJhNTQ1YjI5YzcxODcifX19",
         item = Item.BLOCK_DOWN
      )
      back = createCustomSkull(
         name = "Zurück",
         value = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWYxMzNlOTE5MTlkYjBhY2VmZGMyNzJkNjdmZDg3YjRiZTg4ZGM0NGE5NTg5NTg4MjQ0NzRlMjFlMDZkNTNlNiJ9fX0=",
         item = Item.BLOCK_RETURN
      )
      information = createCustomSkull(
         name = "Neue Blöcke am",
         color = NamedTextColor.GOLD,
         value = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmMyNzEwNTI3MTllZjY0MDc5ZWU4YzE0OTg5NTEyMzhhNzRkYWM0YzI3Yjk1NjQwZGI2ZmJkZGMyZDZiNWI2ZSJ9fX0=",
         item = Item.TIME_INFO
      )
      newBlocks = createCustomSkull(
         name = "Neue Blöcke für alle",
         color = NamedTextColor.YELLOW,
         value = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDdhMGZjNmRjZjczOWMxMWZlY2U0M2NkZDE4NGRlYTc5MWNmNzU3YmY3YmQ5MTUzNmZkYmM5NmZhNDdhY2ZiIn19fQ==",
         item = Item.NEW_BLOCKS
      )
   }
}
private fun createCustomSkull(name: String, color: NamedTextColor = NamedTextColor.WHITE, value: String, item: Item, customPDC: List<CustomPDC>? = null): ItemStack {
   val skullItem = ItemStack(Material.PLAYER_HEAD)
   val skullMeta = skullItem.itemMeta as SkullMeta
   val profile = Bukkit.createProfileExact(UUID.randomUUID(), "Skull")
   profile.setProperty(ProfileProperty("textures", value, value))
   skullMeta.playerProfile = profile
   customPDC?.forEach { skullMeta.persistentDataContainer.set(NamespacedKey(Main.INSTANCE, it.namespacedKey), PersistentDataType.STRING, it.data.toString()) }
   skullMeta.displayName(Component.text(name).color(color).decoration(TextDecoration.ITALIC, false))
   skullMeta.persistentDataContainer.set(NamespacedKey(Main.INSTANCE, "itemType"), PersistentDataType.STRING, item.pdc)
   skullItem.itemMeta = skullMeta
   return skullItem
}
fun ItemStack.addPDC(data: List<CustomPDC>): ItemStack {
   val meta = itemMeta
   data.forEach { meta.persistentDataContainer.set(NamespacedKey(Main.INSTANCE, it.namespacedKey), PersistentDataType.STRING, it.data.toString()) }
   itemMeta = meta
   return this
}
fun ItemStack.addLore(lore: String): ItemStack {
   val meta = itemMeta
   meta.lore(listOf(Component.text(lore).color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)))
   itemMeta = meta
   return this
}