package be.julien.donjon.world

import be.julien.donjon.Drawer
import be.julien.donjon.Util

/**
 * Created by julien on 04/07/17.
 */
class World {
    internal val squares = Array(WorldCst.dim.surface, {i -> Square(Util.lineToRow(i, WorldCst.dim.col), Util.lineToCol(i, WorldCst.dim.col))})

    fun act() {
    }

    fun draw(drawer: Drawer) {
        squares.forEach { s -> s.draw(drawer) }
    }
}

object WorldCst {
    val dim = Dimension(2, 2)
}
