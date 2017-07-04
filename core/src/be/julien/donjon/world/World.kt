package be.julien.donjon.world

import be.julien.donjon.Utility

/**
 * Created by julien on 04/07/17.
 */
class World {
    internal val squares = Array(WorldCst.dim.surface, {Square()})
    fun act() {
        Utility.out("act")
    }
    fun draw() {
        Utility.out("draw")
    }
}

object WorldCst {
    val dim = Dimension(10, 10)
}
