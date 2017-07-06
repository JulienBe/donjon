package be.julien.donjon.util

import be.julien.donjon.spatial.Direction
import java.util.*

object Rnd {
    internal val rnd = Random()

    fun int(max: Int): Int {
        return rnd.nextInt(max)
    }

    fun float(mult: Float = 1f): Float {
        return rnd.nextFloat() * mult
    }

    fun direction(): Direction {
        return Direction.values()[int(Direction.values().size)]
    }
}