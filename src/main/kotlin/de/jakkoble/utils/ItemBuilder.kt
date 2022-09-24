package de.jakkoble.utils

import de.jakkoble.Main
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

fun createItem(material: Material, item: Item, name: String = "", color: NamedTextColor = NamedTextColor.WHITE, lore: List<String>? = null, customPDC: List<CustomPDC>? = null): ItemStack {
   val itemStack = ItemStack(material)
   val itemMeta = itemStack.itemMeta ?: return itemStack
   if (name != "") itemMeta.displayName(Component.text(name).color(color).decoration(TextDecoration.ITALIC, false))
   else itemMeta.displayName(Component.text(""))
   if (lore != null) {
      val itemLore = mutableListOf<Component>()
      lore.forEach { itemLore.add(Component.text(it).color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)) }
      itemMeta.lore(itemLore)
   }
   itemMeta.persistentDataContainer.set(NamespacedKey(Main.INSTANCE, "itemType"), PersistentDataType.STRING, item.pdc)
   customPDC?.forEach { itemMeta.persistentDataContainer.set(NamespacedKey(Main.INSTANCE, it.namespacedKey), PersistentDataType.STRING, it.data.toString()) }
   itemStack.itemMeta = itemMeta
   return itemStack
}
data class LoreElement(val content: String, val color: NamedTextColor, val underlined: Boolean = false)
enum class Item(val pdc: String) {
   BLOCKS_COLOR("blockColor"),
   PERSONAL_BLOCKS("personalBlocks"),
   DEFAULT_BLOCKS("defaultBlocks"),
   BLOCK_RETURN("returnBlock"),
   BLOCK("block"),
   BLOCK_DOWN("blockDown"),
   TIME_INFO("timeInfo"),
   BLOCK_UP("blockUp"),
   SETTINGS_INVTERVALL("intervall"),
   SETTINGS_UPTIME("uptime"),
   PLACEHOLDER("placeholder"),
   NONE("none")
}
fun ItemStack.itemType(): Item {
   val pdc = itemMeta?.persistentDataContainer?.get(NamespacedKey(Main.INSTANCE, "itemType"), PersistentDataType.STRING) ?: return Item.NONE
   var invite: Item = Item.NONE
   Item.values().forEach { if (it.pdc == pdc) invite = it }
   return invite
}
data class CustomPDC(val namespacedKey: String, val data: Any)
fun ItemStack.getPDC(namespacedKey: String): String? = itemMeta.persistentDataContainer.get(NamespacedKey(Main.INSTANCE, namespacedKey), PersistentDataType.STRING)