package be.julien.donjon.world

import be.julien.donjon.GdxArr
import be.julien.donjon.graphics.Drawer
import be.julien.donjon.Util
import be.julien.donjon.life.Life
import be.julien.donjon.spatial.Dimension

class World {

    internal val squares = Array(WorldWiz.dim.surface, { i -> Square(Util.lineToRow(i, WorldWiz.dim.col), Util.lineToCol(i, WorldWiz.dim.col))})
    internal val lifeforms = GdxArr<Life>()

    fun act() {

    }

    fun draw(drawer: Drawer) {
        squares.forEach { s -> s.draw(drawer) }
    }
}

object WorldWiz {
    val dim = Dimension(2, 2)
    fun trim(i: Int, max: Int): Int {
        if (i > 0)
            if (i < max)
                return i
            else
                return max
        return 0
    }
    fun trimX(x: Int): Int {
        return trim(x, dim.col)
    }
    fun trimY(y: Int): Int {
        return trim(y, dim.row)
    }
}
