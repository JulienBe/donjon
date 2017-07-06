package be.julien.donjon.world

import be.julien.donjon.GdxArr
import be.julien.donjon.graphics.Drawer
import be.julien.donjon.util.Util
import be.julien.donjon.life.Life
import be.julien.donjon.life.MostBasic
import be.julien.donjon.spatial.Dimension
import be.julien.donjon.spatial.PositionPool
import be.julien.donjon.util.Rnd

class World {

    internal val squares = Array(WorldWiz.dim.surface, { i ->
        Square(PositionPool.get(Util.lineToRow(i, WorldWiz.dim.col), Util.lineToCol(i, WorldWiz.dim.col)))})
    internal val lifeforms = GdxArr<Life>()

    fun act() {

    }

    fun draw(drawer: Drawer) {
        squares.forEach { s -> s.draw(drawer) }
        lifeforms.forEach { l -> l.draw(drawer) }
    }

    fun spawn() {
        for (i in 1 .. WorldWiz.initLife)
            lifeforms.add(newLife())
    }

    private fun newLife(): Life {
        return MostBasic(PositionPool.get(
                    Rnd.int(WorldWiz.dim.col),
                    Rnd.int(WorldWiz.dim.row))
                )
    }
}

object WorldWiz {
    val dim = Dimension(40, 40)
    val initLife = 10
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
