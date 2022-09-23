package de.jakkoble.utils

import net.axay.kspigot.runnables.sync
import net.axay.kspigot.runnables.task
import org.bukkit.Material
import org.bukkit.inventory.Inventory

fun Inventory.transition(destinationInv: Inventory) {
   if (this.size != destinationInv.size) throw IllegalArgumentException("Inventory size is not equal")
   val rows = this.size/9
   task (true, 0, 1, rows.toLong()) { task ->
      val round = task.counterUp ?: return@task
      sync {
         val row = round - 1
         task(true, 0, 1, 9) {
            val slot = (it.counterDownToZero ?: 0) + row * 9
            this.setItem((slot).toInt(), destinationInv.getItem((slot).toInt()))
         }
      }
   }
}
fun Inventory.fillBorder() {
   val placeholder = createItem(
      material = Material.GRAY_STAINED_GLASS_PANE,
      item = Item.PLACEHOLDER
   )
   val rows = size / 9
   for (i in 0 until rows) {
      if (i == 0 || i == rows - 1) for (x in 0..8) setItem(x + i * 9, placeholder)
      else {
         setItem(0 + i * 9, placeholder)
         setItem(8 + i * 9, placeholder)
      }
   }
}