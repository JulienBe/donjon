package be.julien.donjon.util

import be.julien.donjon.graphics.Drawer
import java.util.*

object Rnd {
    internal val rnd = Random()

    fun int(max: Int): Int {
        return rnd.nextInt(max)
    }

    fun float(mult: Float = 1f): Float {
        return rnd.nextFloat() * mult
    }

    fun float(): Float = rnd.nextFloat()

    fun width(): Float = float(Drawer.screenWidth)
    fun height(): Float = float(Drawer.screenHeight)
}